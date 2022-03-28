package cn.cocowwy.showdbcore.entities;

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
     * URL
     */
    private String url;

    /**
     * 连接 地址
     */
    private String host;

    /**
     * 连接 端口
     */
    private String port;

    /**
     * 账号名
     */
    private String username;

    /**
     * 数据库产品名
     */
    private String dsProductName;

    /**
     * 密码
     */
    private transient String password;

    /**
     * 当前数据源是否在使用
     */
    private Boolean use = Boolean.FALSE;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
