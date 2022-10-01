package cn.cocowwy.showdbui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Configuration("ShowDBWebConfig")
public class ShowDBWebConfig {
    /**
     * if context-path un config,set null
     */
    @Value("${server.servlet.context-path:#{null}}")
    public String contextPath;
}
