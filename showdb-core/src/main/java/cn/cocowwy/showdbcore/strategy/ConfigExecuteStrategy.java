package cn.cocowwy.showdbcore.strategy;

/**
 * @author Cocowwy
 * @create 2022-03-03-20:06
 */
public interface ConfigExecuteStrategy extends SqlExecuteStrategy {
    /**
     * 操作系统版本
     * @return
     */
    String OsEnv(String ds);

    /**
     * 数据库版本号
     * @return
     */
    String DbVersion(String ds);

    /**
     * 路径
     * @return
     */
    String baseDir(String ds);
}
