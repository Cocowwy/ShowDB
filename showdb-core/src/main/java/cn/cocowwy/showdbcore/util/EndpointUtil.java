package cn.cocowwy.showdbcore.util;

import cn.cocowwy.showdbcore.constants.EndpointEnum;
import cn.cocowwy.showdbcore.config.GlobalContext;

import java.util.Set;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-19:20
 */
public class EndpointUtil {
    /**
     * 设置开启的功能端点
     * @param endpoints
     */
    public static void setEnableSet(Set<String> endpoints) {
        if (endpoints.contains(EndpointEnum.ALL.getName())) {
            GlobalContext.enableAllEndpoint();
        }

        Set<EndpointEnum> enableEndpoint = GlobalContext.getEnableEndpoint();
        for (EndpointEnum ed : EndpointEnum.values()) {
            if (endpoints.contains(ed.getName())) {
                enableEndpoint.add(ed);
            }
        }
    }
}
