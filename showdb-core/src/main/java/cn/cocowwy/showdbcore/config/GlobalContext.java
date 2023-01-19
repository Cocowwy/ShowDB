package cn.cocowwy.showdbcore.config;

import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.entities.ShowDBConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-12:51
 */
public final class GlobalContext {

    private static final Log logger = LogFactory.getLog(GlobalContext.class);

    /**
     * 数据源集合，适配多数据源情况下进行切换  beanName --> dataSource
     */
    private static Map<String, DataSource> dataSourcesMap;

    /**
     * 数据源所对应的数据库类型，通过beanName对应  beanName --> databaseType
     */
    private static Map<String, DBEnum> dataSourcesTypeMap;

    /**
     * 持有 jdbcTemplate
     */
    private static final Map<String, JdbcTemplate> jdbcTemplatePool = new HashMap<>(1);


    /**
     * 配置信息
     */
    private static ShowDBConfig showDBConfig;

    /**
     * 持有context
     */
    private static ApplicationContext applicationContext;

    public static void initialize(Map<String, DataSource> dataSourcesMap, Map<String, DBEnum> dataSourcesTypeMap, ApplicationContext applicationContext) {
        GlobalContext.dataSourcesMap = dataSourcesMap;
        GlobalContext.dataSourcesTypeMap = dataSourcesTypeMap;
        GlobalContext.applicationContext = applicationContext;
        dataSourcesMap.forEach((key, value) -> {
            jdbcTemplatePool.put(key, new JdbcTemplate(value));
            logger.info(String.format("ShowDB register DataSource [%s]", key));
        });
    }

    public static Map<String, DataSource> getDataSourcesMap() {
        return dataSourcesMap;
    }

    public static Map<String, DBEnum> getDataSourcesTypeMap() {
        return dataSourcesTypeMap;
    }

    public static DBEnum mapDs2DbType(String ds) {
        return dataSourcesTypeMap.get(ds);
    }

    public static ShowDBConfig getShowDBConfig() {
        return showDBConfig;
    }

    public static void setShowDBConfig(ShowDBConfig showDBConfig) {
        GlobalContext.showDBConfig = showDBConfig;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取当前所选择数据源的JdbcTemplate对象
     * @return JdbcTemplate
     */
    public static JdbcTemplate getJdbcTemplate(String ds) {
        return jdbcTemplatePool.get(ds);
    }
}

