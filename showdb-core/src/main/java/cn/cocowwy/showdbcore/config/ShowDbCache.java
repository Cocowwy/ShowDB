package cn.cocowwy.showdbcore.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ShowDB's simple cache
 *
 * @author Cocowwy
 */
public class ShowDbCache {
    private static final Log logger = LogFactory.getLog(ShowDbCache.class);

    /**
     * 通用 key：ds#biz#label
     * ds 数据源类型
     * biz 业务标识
     * label 唯一标识
     */
    private static final Map<String, Object> CACHE = new ConcurrentHashMap<>(16);

    /**
     * 缓存清除器
     */
    private static ScheduledThreadPoolExecutor cleanExecutor;

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
        logger.info("Clean ShowDB cache");
        CACHE.clear();
    }

    /**
     * 缓存键构造
     * Cache key construction
     *
     * @param ds    数据源
     * @param biz   业务标识
     * @param label 唯一标识
     * @return 缓存键
     */
    public static String buildCacheKey(String ds, String biz, String label) {
        return String.format("%s#%s#%s", ds, biz, label);
    }

    public static Map<String, Object> cache() {
        return CACHE;
    }

    public static void addCacheTask(Long timeout) {
        if (timeout <= 0L) {
            logger.info("Cache cleaning unregistered, the timeout is " + timeout);
            return;
        }

        cleanExecutor = new ScheduledThreadPoolExecutor(1);
        cleanExecutor.scheduleAtFixedRate(ShowDbCache::clean, timeout, timeout, TimeUnit.SECONDS);
        logger.info("ShowDB cache cleaner registered");
    }

    public static void shutdownCleanCache() {
        if (Objects.nonNull(cleanExecutor)) {
            cleanExecutor.shutdown();
        }
    }
}
