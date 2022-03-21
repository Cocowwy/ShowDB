package cn.cocowwy.showdbcore.config;

import cn.cocowwy.showdbcore.util.DataSourcePropUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据源连接工厂
 *
 * @author Cocowwy
 * @create 2022-03-03-23:36
 */
public class ShowDbFactory {
    private static final Log logger = LogFactory.getLog(ShowDbFactory.class);
    public static final ShowDbFactory INSTANCE = new ShowDbFactory();
    private static DataSource dataSource;
    private static JdbcTemplate jdbcTemplate;

    private ShowDbFactory() {
    }

    public void init(DataSource ds) {
        try {
            logger.info(String.format("current dbshow datasource switch to %s", DataSourcePropUtil.getBeanName(ds)));
        } catch (SQLException throwables) {
            // ignore..
        }
        jdbcTemplate = new JdbcTemplate(ds);
        dataSource = ds;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
