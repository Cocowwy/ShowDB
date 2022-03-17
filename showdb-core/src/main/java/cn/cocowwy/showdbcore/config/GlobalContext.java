package cn.cocowwy.showdbcore.config;

import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.constants.EndpointEnum;
import cn.cocowwy.showdbcore.exception.ShowDbException;
import cn.cocowwy.showdbcore.util.DataSourcePropUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-12:51
 */
public class GlobalContext {
    private static final Log logger = LogFactory.getLog(GlobalContext.class);
    /**
     * 已开启端点的功能集合
     */
    private static final Set<EndpointEnum> ENABLE_ENDPOINT = new HashSet<EndpointEnum>(1);

    /**
     * 当前使用的数据源类型
     */
    private static DBEnum database;

    /**
     * 数据源集合，适配多数据源情况下进行切换  beanName --> dataSource
     */
    private static Map<String, DataSource> dataSourcesMap;

    /**
     * 数据源所对应的数据库类型，通过beanName对应  beanName --> databaseType
     */
    private static Map<String, DBEnum> dataSourcesTypeMap;

    /**
     * 环境中存在多数据源情况下，JdbcTemplate进行切换数据源
     * When there are multiple data sources in the environment, perform manual switching
     * @param dataSourceName
     */
    public static void switchDataSource(String dataSourceName) throws SQLException {
        DataSource ds = dataSourcesMap.get(dataSourceName);
        if (ds == null) {
            throw new ShowDbException("a data source with the current name does not exist");
        }
        ShowDbFactory.INSTANCE.init(ds);
        GlobalContext.setDatabase(DataSourcePropUtil.dataSourceType(ds));
        logger.info(String.format("switch datasource to [%s]", dataSourceName));
    }

    /**
     * 设置数据源集合，兼容多数据源场景
     * @param dataSourcesMap
     */
    public static void setDataSourcesMap(Map<String, DataSource> dataSourcesMap) {
        GlobalContext.dataSourcesMap = dataSourcesMap;
    }

    public static Set<EndpointEnum> getEnableEndpoint() {
        return ENABLE_ENDPOINT;
    }

    public static Map<String, DataSource> getDataSourcesMap() {
        return dataSourcesMap;
    }

    /**
     * 开启所有
     */
    public static void enableAllEndpoint() {
        ENABLE_ENDPOINT.addAll(Arrays.asList(EndpointEnum.values()));
    }

    public static Boolean isEnable(EndpointEnum endpoint) {
        return ENABLE_ENDPOINT.contains(endpoint);
    }

    public static String getDatabaseProductName() {
        return database.getName();
    }

    public static DBEnum getDatabase() {
        return database;
    }

    public static void setDatabase(DBEnum db) {
        GlobalContext.database = db;
    }

    public static Map<String, DBEnum> getDataSourcesTypeMap() {
        return dataSourcesTypeMap;
    }

    public static void setDataSourcesTypeMap(Map<String, DBEnum> dataSourcesTypeMap) {
        GlobalContext.dataSourcesTypeMap = dataSourcesTypeMap;
    }
}

