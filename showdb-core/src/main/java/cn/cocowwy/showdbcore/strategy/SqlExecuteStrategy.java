package cn.cocowwy.showdbcore.strategy;

import cn.cocowwy.showdbcore.config.ShowDbFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-19:33
 */
public interface SqlExecuteStrategy  {
    JdbcTemplate jdbcTemplate = ShowDbFactory.getJdbcTemplate();
}
