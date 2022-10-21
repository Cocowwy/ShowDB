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
     * 缓存刷新时间
     * 小于0时 不自动刷新，默认值为 -1
     * 单位为 秒
     */
    private Long refresh = -1L;
    /**
     * 自定义
     */
    private Customize customize;
    /**
     * 插件生成
     */
    private Plugin plugin = new Plugin();

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
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

    public Plugin getPlugin() {
        return plugin;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }
}
