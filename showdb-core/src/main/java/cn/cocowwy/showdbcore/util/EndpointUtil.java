package cn.cocowwy.showdbcore.util;

import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.constants.EndpointEnum;

import java.util.Set;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-19:20
 */
@Deprecated
public class EndpointUtil {
    /**
     * 设置开启的功能端点
     * @param endpoints
     */
    public static void setEnableSet(Set<String> endpoints) {
        if (endpoints.contains(EndpointEnum.ALL.getName())) {
            GlobalContext.enableAllEndpoint();
            return;
        }

        Set<EndpointEnum> enableEndpoint = GlobalContext.getEnableEndpoint();
        for (EndpointEnum ed : EndpointEnum.values()) {
            if (endpoints.contains(ed.getName())) {
                enableEndpoint.add(ed);
            }
        }
    }

    /**
     * 是否可以使用该端点功能
     * @param ed
     * @return
     */
    public static Boolean canPass(EndpointEnum ed) {
        return GlobalContext.getEnableEndpoint().contains(ed)
                || ed.equals(EndpointEnum.ALL)
                || GlobalContext.getEnableEndpoint().contains(EndpointEnum.ALL)
                ? Boolean.TRUE : Boolean.FALSE;
    }
}
