package cn.cocowwy.showdbcore.entities;

/**
 * @author Cocowwy
 * @create 2022-03-03-20:42
 */
public class SlaveStatus {
    private String slaveIOState;
    private String masterHost;
    private String masterUser;
    private String masterPort;
    private String connectRetry;
    private String masterLogFile;
    private String readMasterLogPos;
    private String relayLogFile;
    private String relayLogPos;
    private String relayMasterLogFile;
    private String slaveIORunning;
    private String slaveSQLRunning;
    private String stringreplicateDoDB;
    private String replicateIgnoreDB;

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

    public String getConnectRetry() {
        return connectRetry;
    }

    public void setConnectRetry(String connectRetry) {
        this.connectRetry = connectRetry;
    }

    public String getMasterLogFile() {
        return masterLogFile;
    }

    public void setMasterLogFile(String masterLogFile) {
        this.masterLogFile = masterLogFile;
    }

    public String getReadMasterLogPos() {
        return readMasterLogPos;
    }

    public void setReadMasterLogPos(String readMasterLogPos) {
        this.readMasterLogPos = readMasterLogPos;
    }

    public String getRelayLogFile() {
        return relayLogFile;
    }

    public void setRelayLogFile(String relayLogFile) {
        this.relayLogFile = relayLogFile;
    }

    public String getRelayLogPos() {
        return relayLogPos;
    }

    public void setRelayLogPos(String relayLogPos) {
        this.relayLogPos = relayLogPos;
    }

    public String getRelayMasterLogFile() {
        return relayMasterLogFile;
    }

    public void setRelayMasterLogFile(String relayMasterLogFile) {
        this.relayMasterLogFile = relayMasterLogFile;
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

    public String getStringreplicateDoDB() {
        return stringreplicateDoDB;
    }

    public void setStringreplicateDoDB(String stringreplicateDoDB) {
        this.stringreplicateDoDB = stringreplicateDoDB;
    }

    public String getReplicateIgnoreDB() {
        return replicateIgnoreDB;
    }

    public void setReplicateIgnoreDB(String replicateIgnoreDB) {
        this.replicateIgnoreDB = replicateIgnoreDB;
    }
}
