package cn.cocowwy.showdbcore.config;

import java.util.Set;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-12:51
 */
public class GlobalContext {
    /**
     * 已开启端点的功能集合
     */
    private static Set<EndpointEnum> enableEndpoint;

    public static Set<EndpointEnum> getEnableEndpoint() {
        return enableEndpoint;
    }

    /**
     * 开启所有
     */
    public static void enableAllEndpoint() {
        for (EndpointEnum endpoint : EndpointEnum.values()) {
            enableEndpoint.add(endpoint);
        }
    }

    public static Boolean isEnable(EndpointEnum endpoint) {
        return enableEndpoint.contains(endpoint);
    }
}

