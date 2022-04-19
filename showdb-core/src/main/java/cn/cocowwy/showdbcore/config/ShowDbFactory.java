package cn.cocowwy.showdbcore.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private ShowDbFactory() {
    }

    public void init() {
        //fix：多次切换数据源 new JdbcTemplate 占用资源，需要改为map
        GlobalContext.getDataSourcesMap().entrySet().forEach(e -> {
            jdbcTemplatePool.put(e.getKey(), new JdbcTemplate(e.getValue()));
            logger.info(String.format("ShowDB register DataSource [%s]", e.getKey()));
        });
    }

    /**
     * 获取当前所选择数据源的JdbcTemplate对象
     * @return
     */
    public static JdbcTemplate getJdbcTemplate(String ds) {
        return jdbcTemplatePool.get(ds);
    }
}
