package cn.cocowwy.showdbui.service;

import cn.cocowwy.showdbcore.cache.ShowDbCache;
import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.entities.DsInfo;
import cn.cocowwy.showdbcore.strategy.ConfigExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlExecuteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
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
    List<ConfigExecuteStrategy> configExecuteStrategies;
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
    public String getOsEnv() {
        String key = ShowDbCache.buildCacheKey("os", "env");
        String env = (String) ShowDbCache.get(key);
        if (null == env) {
            env = CONFIG_STRATEGY.get(GlobalContext.getDatabase()).OsEnv();
            ShowDbCache.put(key, env);
        }
        return env;
    }

    /**
     * DB版本号
     * @return
     */
    public String getDbVersion() {
        String key = (String) ShowDbCache.buildCacheKey("db", "version");
        String version = (String) ShowDbCache.get(key);
        if (null == version) {
            version = CONFIG_STRATEGY.get(GlobalContext.getDatabase()).DbVersion();
            ShowDbCache.put(key, version);
        }
        return version;
    }

    /**
     * 获取数据源信息
     * @return
     */
    public List<DsInfo> getDsInfo() {
        Map<String, DataSource> dsMap = GlobalContext.getDataSourcesMap();

        List<DsInfo> dsInfos = dsMap.entrySet().stream().map(entry -> {
            DsInfo dsInfo = new DsInfo();
            try {
                DatabaseMetaData masterDataSource = null;
                masterDataSource = dsMap.get(entry.getKey()).getConnection().getMetaData();

                dsInfo.setBeanName(entry.getKey());
                dsInfo.setDsProductName(masterDataSource.getDatabaseProductName());
                dsInfo.setUrl(masterDataSource.getURL());
                // TODO
                return dsInfo;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return dsInfo;
        }).collect(Collectors.toList());

        return dsInfos;
    }
}
