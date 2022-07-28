package cn.cocowwy.showdbcore.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-13:35
 */
public enum EndpointEnum {
    /**
     * 所有
     */
    ALL("*"),
    /**
     * 主从信息监控
     */
    MONITOR_MASTER_SLAVE("monitor-master-slave"),
    /**
     * IP监控
     */
    MONITOR_IP_CONNECTION("monitor-ip-connection"),
    /**
     * 性能监控，事务，锁，等
     */
    MONITOR_PERFORMANCE("monitor-performance");

    private String name;
    private static final Map<String, EndpointEnum> map = new HashMap<String, EndpointEnum>();

    static {
        for (EndpointEnum value : EndpointEnum.values()) {
            map.put(value.getName(), value);
        }
    }

    EndpointEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Map<String, EndpointEnum> getMap() {
        return map;
    }
}
