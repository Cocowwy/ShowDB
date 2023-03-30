package cn.cocowwy.showdbcore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author cocowwy.cn
 */
@Configuration("Defined")
public class DefinedConfig {
    /**
     * if context-path no setting,set null
     */
    @Value("${server.servlet.context-path:#{null}}")
    public String contextPath;

    public String version = "2.0.0";
}
