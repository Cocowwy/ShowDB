package cn.cocowwy.showdbcore.config;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author Cocowwy
 * @create 2022-03-03-23:36
 */
public class ShowDBFactory {
    public static final ShowDBFactory INSTANCE = new ShowDBFactory();
    private static DataSource dataSource;
    private static JdbcTemplate jdbcTemplate;

    private ShowDBFactory() {
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
}
