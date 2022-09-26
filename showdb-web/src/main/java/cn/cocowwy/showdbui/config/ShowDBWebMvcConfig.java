package cn.cocowwy.showdbui.config;

import cn.cocowwy.showdbui.annotation.ShowDBRestController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */

@Configuration
public class ShowDBWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/showdb",
                c -> c.isAnnotationPresent(ShowDBRestController.class));
    }

}