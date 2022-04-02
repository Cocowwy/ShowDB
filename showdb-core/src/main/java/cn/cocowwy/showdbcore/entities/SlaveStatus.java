package cn.cocowwy.showdbcore.entities;

/**
 * @author Cocowwy
 * @create 2022-03-03-20:42
 */
public class SlaveStatus {
    /**
     * 状态
     */
    private String slaveIOState;
    private String masterHost;
    private String masterUser;
    private String masterPort;
    /**
     * 连接中断后，重新尝试连接的时间间隔。默认值是60秒。
     */
    private String masterRetryCount;
    private String slaveIORunning;
    private String slaveSQLRunning;
    /**
     * 一个非负整数，表示秒数，Slave滞后多少秒于master。
     */
    private String sqlDelay;
    /**
     * SQL线程正在读取和执行的中继日志文件
     */
    private String relayLogFile;
    /**
     * 当前I/O线程正在读取的主服务器二进制日志文件的名称
     */
    private String masterLogFile;

    public String getSlaveIOState() {
        return slaveIOState;
    }

    public void setSlaveIOState(String slaveIOState) {
        this.slaveIOState = slaveIOState;
    }

    public String getMasterHost() {
        return masterHost;
    }

    public void setMasterHost(String masterHost) {
        this.masterHost = masterHost;
    }

    public String getMasterUser() {
        return masterUser;
    }

    public void setMasterUser(String masterUser) {
        this.masterUser = masterUser;
    }

    public String getMasterPort() {
        return masterPort;
    }

    public void setMasterPort(String masterPort) {
        this.masterPort = masterPort;
    }

    public String getMasterRetryCount() {
        return masterRetryCount;
    }

    public void setMasterRetryCount(String masterRetryCount) {
        this.masterRetryCount = masterRetryCount;
    }

    public String getSlaveIORunning() {
        return slaveIORunning;
    }

    public void setSlaveIORunning(String slaveIORunning) {
        this.slaveIORunning = slaveIORunning;
    }

    public String getSlaveSQLRunning() {
        return slaveSQLRunning;
    }

    public void setSlaveSQLRunning(String slaveSQLRunning) {
        this.slaveSQLRunning = slaveSQLRunning;
    }

    public String getSqlDelay() {
        return sqlDelay;
    }

    public void setSqlDelay(String sqlDelay) {
        this.sqlDelay = sqlDelay;
    }

    public String getRelayLogFile() {
        return relayLogFile;
    }

    public void setRelayLogFile(String relayLogFile) {
        this.relayLogFile = relayLogFile;
    }

    public String getMasterLogFile() {
        return masterLogFile;
    }

    public void setMasterLogFile(String masterLogFile) {
        this.masterLogFile = masterLogFile;
    }
}
