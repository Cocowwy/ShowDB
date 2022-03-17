package cn.cocowwy.showdbcore.cache;

import cn.cocowwy.showdbcore.constants.CacheKey;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简易缓存工具
 *
 * @author Cocowwy
 * @create 2022-03-03-21:37
 */
public class ShowDbCache {
    private static final Map<CacheKey, Object> CACHE = new ConcurrentHashMap<>(16);

    public static Object put(CacheKey key, Object value) {
        return CACHE.put(key, value);
    }

    public static Object rm(CacheKey key) {
        return CACHE.remove(key);
    }

    public static Object get(CacheKey key) {
        return CACHE.get(key);
    }
}
