package cn.cocowwy.showdb.configuration;

/**
 * @author Cocowwy
 * @create 2022-03-03-22:22
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.Set;

@ConfigurationProperties("showdb")
public class ShowDbProperties {
    /**
     * 是否启用
     */
    private boolean enable = true;
    /**
     * 开启功能端点 以逗号分割
     * *                        默认，代表开启所有功能，生产环境不建议开启
     * structure                表结构，以及扩展功能
     * monitor-master-slave     监控主从库延迟
     * monitor-table            监控表数据，大小索引等
     */
    private Set<String> endpoint = Collections.singleton("*");
    /**
     * 缓存刷新时间
     * 小于0时 不自动刷新，默认值为 -1
     * 单位为 秒
     */
    private Long refresh = -1L;

    /**
     * 自定义
     */
    private Customize customize;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Set<String> getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Set<String> endpoint) {
        this.endpoint = endpoint;
    }

    public Long getRefresh() {
        return refresh;
    }

    public void setRefresh(Long refresh) {
        this.refresh = refresh;
    }

    public Customize getCustomize() {
        return customize;
    }

    public void setCustomize(Customize customize) {
        this.customize = customize;
    }

}
