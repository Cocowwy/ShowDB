package cn.cocowwy.showdbcore.strategy.impl.mysql;

import cn.cocowwy.showdbcore.config.ShowDbFactory;
import cn.cocowwy.showdbcore.strategy.ConfigExecuteStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Cocowwy
 * @create 2022-03-03-20:40
 */
@Component
public class MySqlConfigExecuteStrategy implements ConfigExecuteStrategy, MySqlExecuteStrategy {

    /**
     * 当前数据库的操作系统版本
     * @return
     */
    @Override
    public String OsEnv(String ds) {
        String sql = "show variables like 'version_compile_os'";
        List<String> rts = ShowDbFactory.getJdbcTemplate(ds).query(sql, (rs, i) -> rs.getString("Value"));
        return CollectionUtils.firstElement(rts);
    }

    /**
     * 数据库版本号
     * @return
     */
    @Override
    public String DbVersion(String ds) {
        String sql = "show variables like 'version'";
        List<String> rts = ShowDbFactory.getJdbcTemplate(ds).query(sql, (rs, i) -> rs.getString("Value"));
        return CollectionUtils.firstElement(rts);
    }

    /**
     * 路径
     * @return
     */
    @Override
    public String baseDir(String ds) {
        String sql = "show variables like 'basedir'";
        List<String> rts = ShowDbFactory.getJdbcTemplate(ds).query(sql, (rs, i) -> rs.getString("Value"));
        return CollectionUtils.firstElement(rts);
    }
}
