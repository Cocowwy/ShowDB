package cn.cocowwy.showdbcore.config;

import cn.cocowwy.showdbcore.exception.ShowDbException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Cocowwy
 * @create 2022-03-03-23:36
 */
public class ShowDbFactory {
    private static final Log logger = LogFactory.getLog(ShowDbFactory.class);
    public static final ShowDbFactory INSTANCE = new ShowDbFactory();
    private static DataSource dataSource;
    private static JdbcTemplate jdbcTemplate;
    private static String presentDatasourceName;
    /**
     * 数据源集合，适配多数据源情况下进行切换  beanName --> dataSource
     */
    private static Map<String, DataSource> dataSourcesMap;

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
     * 环境中存在多数据源情况下，进行切换
     * When there are multiple data sources in the environment, perform manual switching
     * @param dataSourceName
     */
    public static void switchDataSource(String dataSourceName) throws SQLException {
        DataSource ds = dataSourcesMap.get(dataSourceName);
        if (ds == null) {
            throw new ShowDbException("a data source with the current name does not exist");
        }
        ShowDbFactory.INSTANCE.init(ds);
        GlobalContext.setDatabase(ds.getConnection().getMetaData().getDatabaseProductName());
        GlobalContext.setDatabaseProductVersion(ds.getConnection().getMetaData().getDatabaseProductVersion());
        ShowDbFactory.presentDatasourceName = dataSourceName;
        logger.info(String.format("switch datasource to [%s]", dataSourceName));
    }

    /**
     * 设置数据源集合，兼容多数据源场景
     * @param dataSourcesMap
     */
    public static void setDataSourcesMap(Map<String, DataSource> dataSourcesMap) {
        ShowDbFactory.dataSourcesMap = dataSourcesMap;
    }

    public static void setPresentDatasourceName(String presentDatasourceName) {
        ShowDbFactory.presentDatasourceName = presentDatasourceName;
    }

    public static String getPresentDatasourceName() {
        return presentDatasourceName;
    }
}
