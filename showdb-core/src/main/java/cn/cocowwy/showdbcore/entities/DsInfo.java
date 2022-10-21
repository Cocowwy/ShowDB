package cn.cocowwy.showdbcore.entities;

import java.util.List;
import java.util.Map;

/**
 * 数据源信息
 * @author Cocowwy
 * @create 2022-03-03-21:54
 */
public class DsInfo {
    /**
     * 数据源 bean 名称
     */
    private String beanName;

    /**
     * 数据库名
     */
    private String tableSchema;

    /**
     * URL
     */
    private String url;

    /**
     * 账号名
     */
    private String username;

    /**
     * 数据库产品名
     */
    private String dsProductName;

    /**
     * 当前数据源是否在使用
     */
    private Boolean use = Boolean.FALSE;

    /**
     * 数据大小
     */
    private String dataSize;

    /**
     * 索引大小
     */
    private String indexSize;

    /**
     * 记录数
     */
    private Long records;

    /**
     * 操作系统环境
     */
    private String osEnv;

    /**
     * 数据库版本号
     */
    private String dbVersion;

    /**
     * 路径
     */
    private String baseDir;

    /**
     * 客户端连接数
     */
    private List<IpCount> ipConCounts;

    /**
     * 事务隔离级别
     */
    private String transactionIsolation;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDsProductName() {
        return dsProductName;
    }

    public void setDsProductName(String dsProductName) {
        this.dsProductName = dsProductName;
    }

    public Boolean getUse() {
        return use;
    }

    public void setUse(Boolean use) {
        this.use = use;
    }

    public String getDataSize() {
        return dataSize;
    }

    public void setDataSize(String dataSize) {
        this.dataSize = dataSize;
    }

    public String getIndexSize() {
        return indexSize;
    }

    public void setIndexSize(String indexSize) {
        this.indexSize = indexSize;
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public String getOsEnv() {
        return osEnv;
    }

    public void setOsEnv(String osEnv) {
        this.osEnv = osEnv;
    }

    public String getDbVersion() {
        return dbVersion;
    }

    public void setDbVersion(String dbVersion) {
        this.dbVersion = dbVersion;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public List<IpCount> getIpConCounts() {
        return ipConCounts;
    }

    public void setIpConCounts(List<IpCount> ipConCounts) {
        this.ipConCounts = ipConCounts;
    }

    public String getTransactionIsolation() {
        return transactionIsolation;
    }

    public void setTransactionIsolation(String transactionIsolation) {
        this.transactionIsolation = transactionIsolation;
    }
}
