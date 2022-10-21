package cn.cocowwy.showdbcore.config;

import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.entities.ShowDBConfig;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-12:51
 */
public class GlobalContext {
    /**
     * 数据源集合，适配多数据源情况下进行切换  beanName --> dataSource
     */
    private static Map<String, DataSource> dataSourcesMap;

    /**
     * 数据源所对应的数据库类型，通过beanName对应  beanName --> databaseType
     */
    private static Map<String, DBEnum> dataSourcesTypeMap;

    /**
     * 配置信息
     */
    private static ShowDBConfig showDBConfig;

    /**
     * 设置数据源集合，兼容多数据源场景
     *
     * @param dataSourcesMap
     */
    public static void setDataSourcesMap(Map<String, DataSource> dataSourcesMap) {
        GlobalContext.dataSourcesMap = dataSourcesMap;
    }


    public static Map<String, DataSource> getDataSourcesMap() {
        return dataSourcesMap;
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

    public static ShowDBConfig getShowDBConfig() {
        return showDBConfig;
    }

    public static void setShowDBConfig(ShowDBConfig showDBConfig) {
        GlobalContext.showDBConfig = showDBConfig;
    }
}

