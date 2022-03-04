package cn.cocowwy.showdbcore.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-13:35
 */
public enum EndpointEnum {
    ALL("*"),
    STRUCTURE("structure"),
    MONITOR_MASTER_SLAVE("monitor-master-slave"),
    MONITOR_TABLE("monitor-table");

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
