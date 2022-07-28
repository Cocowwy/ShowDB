package cn.cocowwy.showdbcore.config;

import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.constants.EndpointEnum;
import cn.cocowwy.showdbcore.entities.Customize;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-12:51
 */
public class GlobalContext {
    /**
     * 已开启端点的功能集合
     */
    private static final Set<EndpointEnum> ENABLE_ENDPOINT = new HashSet<EndpointEnum>(1);

    /**
     * 数据源集合，适配多数据源情况下进行切换  beanName --> dataSource
     */
    private static Map<String, DataSource> dataSourcesMap;

    /**
     * 数据源所对应的数据库类型，通过beanName对应  beanName --> databaseType
     */
    private static Map<String, DBEnum> dataSourcesTypeMap;

    /**
     * 用户的自定义信息
     */
    private static Customize customize;

    /**
     * 设置数据源集合，兼容多数据源场景
     *
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
        ENABLE_ENDPOINT.add(EndpointEnum.ALL);
    }

    public static Boolean isEnable(EndpointEnum endpoint) {
        return ENABLE_ENDPOINT.contains(endpoint);
    }

    public static Map<String, DBEnum> getDataSourcesTypeMap() {
        return dataSourcesTypeMap;
    }

    public static void setDataSourcesTypeMap(Map<String, DBEnum> dataSourcesTypeMap) {
        GlobalContext.dataSourcesTypeMap = dataSourcesTypeMap;
    }

    public static DBEnum mapDs2DbType(String ds) {
        return dataSourcesTypeMap.get(ds);
    }

    public static Customize getCustomize() {
        return customize;
    }

    public static void setCustomize(Customize customize) {
        GlobalContext.customize = customize;
    }
}

