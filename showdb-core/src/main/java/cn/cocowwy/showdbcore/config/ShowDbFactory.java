package cn.cocowwy.showdbcore.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据源连接工厂
 *
 * @author Cocowwy
 * @create 2022-03-03-23:36
 */
public class ShowDbFactory {
    private static final Log logger = LogFactory.getLog(ShowDbFactory.class);
    public static final ShowDbFactory INSTANCE = new ShowDbFactory();
    private static final Map<String, JdbcTemplate> jdbcTemplatePool = new HashMap<>(1);

    private static ApplicationContext applicationContext;

    private ShowDbFactory() {
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void init(ApplicationContext context) {
        //fix：多次切换数据源 new JdbcTemplate 占用资源，需要改为map
        GlobalContext.getDataSourcesMap().forEach((key, value) -> {
            jdbcTemplatePool.put(key, new JdbcTemplate(value));
            logger.info(String.format("ShowDB register DataSource [%s]", key));
        });
        // holder spring context
        applicationContext = context;
    }

    /**
     * 获取当前所选择数据源的JdbcTemplate对象
     * @return JdbcTemplate
     */
    public static JdbcTemplate getJdbcTemplate(String ds) {
        return jdbcTemplatePool.get(ds);
    }
}
