package cn.cocowwy.showdbcore.entities;

/**
 * IP-COUNT连接数
 * @author Cocowwy
 * @create 2022-03-03-22:05
 */
public class IpCount {
    /**
     * IP
     */
    private String ip;
    /**
     * 连接数
     */
    private Long count;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
