package cn.cocowwy.showdbautoconfigure.configuration;

/**
 * @author Cocowwy
 * @create 2022-03-03-22:22
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("showdb")
public class ShowDBProperties {
    /**
     * 是否启用
     */
    private boolean enable = true;
}
