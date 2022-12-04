package cn.cocowwy.showdbcore.strategy.impl.mysql;

import cn.cocowwy.showdbcore.config.ShowDbFactory;
import cn.cocowwy.showdbcore.strategy.ConfigExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.MySqlExecuteStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Cocowwy
 * @create 2022-03-03-20:40
 */
@Component
public class MySqlConfigExecuteStrategy extends MySqlExecuteStrategy implements ConfigExecuteStrategy {

    /**
     * 当前数据库的操作系统版本
     * @return
     */
    @Override
    public String OsEnv(String ds) {
        String sql = "show variables like 'version_compile_os'";
        List<String> rts = ShowDbFactory.getJdbcTemplate(ds).query(sql, (rs, i) -> rs.getString("Value"));
        return CollectionUtils.lastElement(rts);
    }

    /**
     * 数据库版本号
     * @return
     */
    @Override
    public String DbVersion(String ds) {
        String sql = "show variables like 'version'";
        List<String> rts = ShowDbFactory.getJdbcTemplate(ds).query(sql, (rs, i) -> rs.getString("Value"));
        return CollectionUtils.lastElement(rts);
    }

//    /**
//     * mysql参数详情
//     */
//    public MySqlVariables variables(String ds) {
//        String key = ShowDbCache.buildCacheKey(ds, "variables", ds);
//
//        ShowDbCache.cache().computeIfAbsent(key, (k) -> {
//            String sql = "show variables where Variable_name\n" +
//                    "in ('version_compile_os',\n" +
//                    "    'autocommit',\n" +
//                    "    'version', 'basedir',\n" +
//                    "    'default_storage_engine',\n" +
//                    "   'transaction_isolation',\n" +
//                    "   'innodb_lock_wait_timeout');\n";
//            ShowDbFactory.getJdbcTemplate(ds).query(sql, (rs, i) -> {
//                MySqlVariables variable = new MySqlVariables();
//                variable.setVersionCompileOs(rs.getString("version_compile_os"));
//                return null;
//            }
//        });
//        return null;
//    }

    /**
     * 获取Innodb锁等待超时时间
     * @param ds
     * @return 超时时间
     */
    @Override
    public String innodbLockWaitTimeout(String ds) {
        String sql = "show variables like 'innodb_lock_wait_timeout'";
        List<String> rts = ShowDbFactory.getJdbcTemplate(ds).query(sql, (rs, i) -> rs.getString("Value"));
        return CollectionUtils.lastElement(rts);
    }

    /**
     * 获取事务隔离级别
     * @param ds 数据源
     * @return
     */
    @Override
    public String transactionIsolation(String ds) {
        String sql = "show variables like 'transaction_isolation'";
        List<String> rts = ShowDbFactory.getJdbcTemplate(ds).query(sql, (rs, i) -> rs.getString("Value"));
        return CollectionUtils.lastElement(rts);
    }

    /**
     * 路径
     * @return
     */
    @Override
    public String baseDir(String ds) {
        String sql = "show variables like 'basedir'";
        List<String> rts = ShowDbFactory.getJdbcTemplate(ds).query(sql, (rs, i) -> rs.getString("Value"));
        return CollectionUtils.lastElement(rts);
    }
}