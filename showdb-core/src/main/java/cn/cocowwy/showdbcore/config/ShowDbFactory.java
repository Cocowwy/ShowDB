package cn.cocowwy.showdbcore.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
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
     * 如果你的容器环境存在多个数据源切换，请调用此方法手动切换
     * If there are multiple data source switches in your container environment, please call this method to switch manually
     * @param ds
     */
    public static void switchDataSource(DataSource ds) throws SQLException {
        ShowDbFactory.INSTANCE.init(ds);
        GlobalContext.setDatabase(ds.getConnection().getMetaData().getDatabaseProductName());
        GlobalContext.setDatabaseProductVersion(ds.getConnection().getMetaData().getDatabaseProductVersion());
    }
}
