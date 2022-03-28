package cn.cocowwy.showdbcore.cache;

import cn.cocowwy.showdbcore.config.GlobalContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * ShowDB的简易缓存工具
 * Simple caching tool
 *
 * @author Cocowwy
 * @create 2022-03-03-21:37
 */
public class ShowDbCache {
    /**
     * 通用key：ds#biz#label
     * ds 数据源类型
     * biz 业务标识
     * label 唯一标识
     */
    private static final Map<String, Object> CACHE = new ConcurrentHashMap<>(16);

    public static Object put(String key, Object value) {
        return CACHE.put(key, value);
    }

    public static Object rm(String key) {
        return CACHE.remove(key);
    }

    public static Object get(String key) {
        return CACHE.get(key);
    }

    public static void clean() {
        CACHE.clear();
    }

    /**
     * 缓存键构造
     * Cache key construction
     * @param ds 数据源
     * @param biz 业务标识
     * @param label 唯一标识
     * @return
     */
    public static String buildCacheKey(String ds, String biz, String label) {
        return String.format("%s#%s#%s", ds, biz, label);
    }


    /**
     * 缓存键构造
     * Cache key construction
     * @param biz 业务标识
     * @param label 唯一标识
     * @return
     */
    public static String buildCacheKey(String biz, String label) {
        return String.format("%s#%s#%s", GlobalContext.getDatabaseProductName(), biz, label);
    }

    public static Map<String, Object>  cache() {
        return CACHE;
    }
}
