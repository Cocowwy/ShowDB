package cn.cocowwy.showdbcore.config;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author Cocowwy
 * @create 2022-03-03-23:36
 */
public class ShowDbFactory {
    public static final ShowDbFactory INSTANCE = new ShowDbFactory();
    private static DataSource dataSource;
    private static JdbcTemplate jdbcTemplate;

    private ShowDbFactory() {
    }

    public void init(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        dataSource = ds;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * 数据源切换
     * @param ds
     */
    public static void switchDataSource(DataSource ds) {
        dataSource = ds;
        jdbcTemplate = new JdbcTemplate(ds);
    }
}
