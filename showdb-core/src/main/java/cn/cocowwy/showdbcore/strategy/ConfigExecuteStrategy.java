package cn.cocowwy.showdbcore.strategy;

/**
 * @author Cocowwy
 * @create 2022-03-03-20:06
 */
public interface ConfigExecuteStrategy extends SqlExecuteStrategy {
    /**
     * 操作系统版本
     * @param ds 数据源
     * @return
     */
    String OsEnv(String ds);

    /**
     * 数据库版本号
     * @param ds 数据源
     * @return
     */
    String DbVersion(String ds);

    /**
     * 路径
     * @param ds 数据源
     * @return
     */
    String baseDir(String ds);

    /**
     * 获取Innodb锁等待超时时间
     * @param ds 数据源
     * @return 超时时间
     */
    String innodbLockWaitTimeout(String ds);
}
