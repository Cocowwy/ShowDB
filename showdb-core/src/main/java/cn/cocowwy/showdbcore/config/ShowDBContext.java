package cn.cocowwy.showdbcore.config;

import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.entities.ShowDBConfig;
import cn.cocowwy.showdbcore.exception.ShowDbException;
import cn.cocowwy.showdbcore.util.DataSourcePropUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ShowDB context
 * @author cocowwy.cn
 */
public final class ShowDBContext {
    private static final Log logger = LogFactory.getLog(ShowDBContext.class);

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

    public static void initialize(ApplicationContext context) {
        applicationContext = context;

        List<String> dataSources = Arrays.asList(applicationContext.getBeanNamesForType(DataSource.class));

        if (CollectionUtils.isEmpty(dataSources)) {
            throw new ShowDbException("Can't find datasource (bean) ,please config it and restart");
        }

        dataSourcesMap = dataSources.stream()
                .collect(Collectors.toMap(Function.identity(), beanName -> (DataSource) applicationContext.getBean(beanName)));

        dataSourcesTypeMap = dataSources.stream()
                .collect(Collectors.toMap(Function.identity(), DataSourcePropUtil::dataSourceTypeByBeanName));

        dataSourcesMap.forEach((key, value) -> {
            jdbcTemplatePool.put(key, new JdbcTemplate(value));
            logger.info(String.format("ShowDB register DataSource [%s]", key));
        });

    }

    /**
     * Get JdbcTemplate by beanName
     * @param ds DataSource bean name
     * @return JdbcTemplate
     */
    public static JdbcTemplate getJdbcTemplate(String ds) {
        return jdbcTemplatePool.get(ds);
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
        ShowDBContext.showDBConfig = showDBConfig;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}

