package cn.cocowwy.showdbcore.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Cocowwy
 * @create 2022-03-03-22:50
 */
public enum DBEnum {
    /**
     * 目前仅支持MYSQL
     */
    MySQL("MySQL");

    public static final Map<String, DBEnum> map;
    public String name;

    DBEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


    static {
        map = new HashMap<String, DBEnum>(1) {{
            put(MySQL.getName(), MySQL);
        }};
    }
}
