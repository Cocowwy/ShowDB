package cn.cocowwy.showdbcore.config;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-12:51
 */
public class GlobalContext {
    private static String endpoint;

    public static String getEndpoint() {
        return endpoint;
    }

    public static void setEndpoint(String endpoint) {
        GlobalContext.endpoint = endpoint;
    }

    public static void parseEndpoint() {
        endpoint.split(",");
    }
}
