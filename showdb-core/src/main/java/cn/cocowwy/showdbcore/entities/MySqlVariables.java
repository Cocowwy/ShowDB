package cn.cocowwy.showdbcore.entities;

/**
 * MySQL配置参数
 * @author Cocowwy
 * @create 2022-04-04-21:31
 */
public class MySqlVariables extends ConfigVariables {
    /**
     * 自动开启事务
     */
    private String autocommit;
    /**
     * 默认的存储引擎
     */
    private String defaultStorageEngine;

    /**
     * 默认事务的隔离级别
     */
    private String transactionIsolation;

    /**
     * 锁等待超时时长
     */
    private String innodbLockWaitTimeout;

    public String getAutocommit() {
        return autocommit;
    }

    public void setAutocommit(String autocommit) {
        this.autocommit = autocommit;
    }

    public String getDefaultStorageEngine() {
        return defaultStorageEngine;
    }

    public void setDefaultStorageEngine(String defaultStorageEngine) {
        this.defaultStorageEngine = defaultStorageEngine;
    }

    public String getTransactionIsolation() {
        return transactionIsolation;
    }

    public void setTransactionIsolation(String transactionIsolation) {
        this.transactionIsolation = transactionIsolation;
    }

    public String getInnodbLockWaitTimeout() {
        return innodbLockWaitTimeout;
    }

    public void setInnodbLockWaitTimeout(String innodbLockWaitTimeout) {
        this.innodbLockWaitTimeout = innodbLockWaitTimeout;
    }
}
