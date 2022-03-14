package cn.cocowwy.showdbcore.config;

import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.constants.EndpointEnum;

import java.util.*;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-12:51
 */
public class GlobalContext {
    /**
     * 已开启端点的功能集合
     */
    private static final Set<EndpointEnum> ENABLE_ENDPOINT = new HashSet<EndpointEnum>(1);
    private static DBEnum database;
    private static String databaseProductVersion;
    private static final Map<String, Object> EXTEND = new HashMap<String, Object>(0);

    public static Set<EndpointEnum> getEnableEndpoint() {
        return ENABLE_ENDPOINT;
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

    public static void setDatabase(String databaseProductName) {
        GlobalContext.database = DBEnum.map.get(databaseProductName);
    }

    public static String getDatabaseProductVersion() {
        return databaseProductVersion;
    }

    public static void setDatabaseProductVersion(String databaseProductVersion) {
        GlobalContext.databaseProductVersion = databaseProductVersion;
    }

    public static Map<String, Object> getEXTEND() {
        return EXTEND;
    }

}

