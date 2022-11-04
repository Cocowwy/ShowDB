package cn.cocowwy.showdbcore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Configuration("Defind")
public class DefindConfig {
    /**
     * if context-path no setting,set null
     */
    @Value("${server.servlet.context-path:#{null}}")
    public String contextPath;
}
