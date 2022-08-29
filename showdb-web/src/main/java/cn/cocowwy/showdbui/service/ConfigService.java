package cn.cocowwy.showdbui.service;

import cn.cocowwy.showdbcore.cache.ShowDbCache;
import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.entities.DsInfo;
import cn.cocowwy.showdbcore.strategy.ConfigExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlExecuteStrategy;
import cn.cocowwy.showdbcore.util.DataSourcePropUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Cocowwy
 * @create 2022-03-03-21:24
 */
@Service
public class ConfigService {
    @Autowired
    private List<ConfigExecuteStrategy> configExecuteStrategies;
    @Autowired
    private MonitorService monitorService;
    /**
     * 数据源类型所对应的执行策略，适配切换数据源能路由到指定策略
     */
    private static final Map<DBEnum, ConfigExecuteStrategy> CONFIG_STRATEGY = new HashMap(1);

    @PostConstruct
    void init() {
        configExecuteStrategies.forEach(s -> {
            if (s instanceof MySqlExecuteStrategy) {
                CONFIG_STRATEGY.put(DBEnum.MySQL, s);
            }
        });
    }

    /**
     * DB所处环境
     * @return
     */
    public String getOsEnv(String ds) {
        String key = ShowDbCache.buildCacheKey(ds, "os", "env");
        return (String) ShowDbCache.cache().computeIfAbsent(key,
                (k) -> CONFIG_STRATEGY.get(GlobalContext.mapDs2DbType(ds)).OsEnv(ds));
    }

    /**
     * DB版本号
     * @return
     */
    public String getDbVersion(String ds) {
        String key = ShowDbCache.buildCacheKey(ds, "db", "version");
        return (String) ShowDbCache.cache().computeIfAbsent(key,
                (k) -> CONFIG_STRATEGY.get(GlobalContext.mapDs2DbType(ds)).DbVersion(ds));
    }

    /**
     * 获取base路径
     * @param ds
     * @return
     */
    private String getBaseDir(String ds) {
        String key = ShowDbCache.buildCacheKey(ds, "db", "baseDir");
        return (String) ShowDbCache.cache().computeIfAbsent(key,
                (k) -> CONFIG_STRATEGY.get(GlobalContext.mapDs2DbType(ds)).baseDir(ds));
    }

    /**
     * 获取事务隔离级别
     * @param ds
     * @return
     */
    private String getTransactionIsolation(String ds) {
        String key = ShowDbCache.buildCacheKey(ds, "db", "getTransactionIsolation");
        return (String) ShowDbCache.cache().computeIfAbsent(key,
                (k) -> CONFIG_STRATEGY.get(GlobalContext.mapDs2DbType(ds)).transactionIsolation(ds));
    }

    /**
     * 获取数据源信息
     * @return
     */
    public List<DsInfo> getDsInfo() {
        Map<String, DataSource> dsMap = GlobalContext.getDataSourcesMap();

        List<DsInfo> dsInfos = dsMap.entrySet().stream().map(entry -> {
            DsInfo dsInfo = new DsInfo();
            Connection connection = null;
            DatabaseMetaData masterDataSource = null;
            try {
                String ds = entry.getKey();
                connection = dsMap.get(ds).getConnection();
                masterDataSource = connection.getMetaData();

                dsInfo.setBeanName(entry.getKey());
                dsInfo.setDsProductName(masterDataSource.getDatabaseProductName());
                dsInfo.setUrl(masterDataSource.getURL());
                dsInfo.setUsername(connection.getMetaData().getUserName());
                dsInfo.setDbVersion(getDbVersion(ds));
                dsInfo.setDataSize(monitorService.dsInfo(ds).getDataSize());
                dsInfo.setIndexSize(monitorService.dsInfo(ds).getIndexSize());
                dsInfo.setRecords(monitorService.dsInfo(ds).getRecords());
                dsInfo.setOsEnv(getDbVersion(ds));
                dsInfo.setOsEnv(getOsEnv(ds));
                dsInfo.setTableSchema(DataSourcePropUtil.getMysqlSchemaFromDataSourceBeanName(ds));
                dsInfo.setBaseDir(this.getBaseDir(ds));
                dsInfo.setTransactionIsolation(this.getTransactionIsolation(ds));
                return dsInfo;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            return dsInfo;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return dsInfos;
    }
}
