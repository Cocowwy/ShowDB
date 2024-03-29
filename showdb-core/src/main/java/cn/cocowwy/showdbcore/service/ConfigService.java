package cn.cocowwy.showdbcore.service;

import cn.cocowwy.showdbcore.config.ShowDbCache;
import cn.cocowwy.showdbcore.config.ShowDBContext;
import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.entities.DsInfo;
import cn.cocowwy.showdbcore.strategy.ConfigExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.MySqlExecuteStrategy;
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
    private static final Map<DBEnum, ConfigExecuteStrategy> CONFIG_STRATEGY = new HashMap<>(1);

    @PostConstruct
    void init() {
        configExecuteStrategies.forEach(s -> {
            if (s instanceof MySqlExecuteStrategy) {
                CONFIG_STRATEGY.put(DBEnum.MySQL, s);
            }
        });
    }

    /**
     * @param ds 当前选择的数据源
     * @return DB所处环境
     */
    public String getOsEnv(String ds) {
        String key = ShowDbCache.buildCacheKey(ds, "os", "env");
        return (String) ShowDbCache.cache().computeIfAbsent(key,
                (k) -> CONFIG_STRATEGY.get(ShowDBContext.mapDs2DbType(ds)).OsEnv(ds));
    }

    /**
     * @param ds 当前选择的数据源
     * @return DB版本号
     */
    public String getDbVersion(String ds) {
        String key = ShowDbCache.buildCacheKey(ds, "db", "version");
        return (String) ShowDbCache.cache().computeIfAbsent(key,
                (k) -> CONFIG_STRATEGY.get(ShowDBContext.mapDs2DbType(ds)).DbVersion(ds));
    }

    /**
     * @param ds 当前选择的数据源
     * @return base路径
     */
    private String getBaseDir(String ds) {
        String key = ShowDbCache.buildCacheKey(ds, "db", "baseDir");
        return (String) ShowDbCache.cache().computeIfAbsent(key,
                (k) -> CONFIG_STRATEGY.get(ShowDBContext.mapDs2DbType(ds)).baseDir(ds));
    }

    /**
     * @param ds 当前选择的数据源
     * @return 事务隔离级别
     */
    private String getTransactionIsolation(String ds) {
        String key = ShowDbCache.buildCacheKey(ds, "db", "getTransactionIsolation");
        return (String) ShowDbCache.cache().computeIfAbsent(key,
                (k) -> CONFIG_STRATEGY.get(ShowDBContext.mapDs2DbType(ds)).transactionIsolation(ds));
    }

    /**
     * @return 数据源信息
     */
    public List<DsInfo> getDsInfo() {
        Map<String, DataSource> dsMap = ShowDBContext.getDataSourcesMap();

        return dsMap.keySet().stream().map(dataSource -> {
            DsInfo dsInfo = new DsInfo();
            Connection connection = null;
            DatabaseMetaData masterDataSource = null;
            try {
                connection = dsMap.get(dataSource).getConnection();
                masterDataSource = connection.getMetaData();

                dsInfo.setBeanName(dataSource);
                dsInfo.setDsProductName(masterDataSource.getDatabaseProductName());
                dsInfo.setUrl(masterDataSource.getURL());
                dsInfo.setUsername(connection.getMetaData().getUserName());
                dsInfo.setDbVersion(getDbVersion(dataSource));
                dsInfo.setDataSize(monitorService.dsInfo(dataSource).getDataSize());
                dsInfo.setIndexSize(monitorService.dsInfo(dataSource).getIndexSize());
                dsInfo.setRecords(monitorService.dsInfo(dataSource).getRecords());
                dsInfo.setOsEnv(getDbVersion(dataSource));
                dsInfo.setOsEnv(getOsEnv(dataSource));
                dsInfo.setTableSchema(DataSourcePropUtil.getMysqlSchemaFromDataSourceBeanName(dataSource));
                dsInfo.setBaseDir(this.getBaseDir(dataSource));
                dsInfo.setTransactionIsolation(this.getTransactionIsolation(dataSource));
                return dsInfo;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    assert connection != null;
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            return dsInfo;
        }).collect(Collectors.toList());
    }
}
