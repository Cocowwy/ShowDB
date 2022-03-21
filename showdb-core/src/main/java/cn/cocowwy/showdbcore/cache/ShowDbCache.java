package cn.cocowwy.showdbcore.cache;

import cn.cocowwy.showdbcore.config.GlobalContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简易缓存工具
 * Simple caching tool
 *
 * @author Cocowwy
 * @create 2022-03-03-21:37
 */
public class ShowDbCache {
    /**
     * ds#biz#label
     * ds 数据源类型
     * biz 业务标识
     * label 唯一标识
     */
    private static final Map<String, Object> GRAINED_CACHE = new ConcurrentHashMap<>(16);

    public static Object put(String key, Object value) {
        return GRAINED_CACHE.put(key, value);
    }

    public static Object rm(String key) {
        return GRAINED_CACHE.remove(key);
    }

    public static Object get(String key) {
        return GRAINED_CACHE.get(key);
    }

    public static void clean() {
        GRAINED_CACHE.clear();
    }

    /**
     * 缓存键构造
     * Cache key construction
     * @param ds 数据源类型
     * @param biz 业务标识
     * @param label 唯一标识
     * @return
     */
    public static String buildCacheKey(String ds, String biz, String label) {
        return String.format("%s#%s#%s", ds, biz, label);
    }
}
