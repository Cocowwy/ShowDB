package cn.cocowwy.showdbautoconfigure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * ShowDB 自动配置类
 *
 * @author Cocowwy
 * @create 2022-03-03-22:13
 */
@ConditionalOnBean(DataSource.class)
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ShowDBProperties.class})
@ConditionalOnProperty(name = "showdb.enable", havingValue = "true")
public class ShowDBAutoConfiguration {
    @Autowired
    private ApplicationContext applicationContext;

    // 通过JDBCTemplate包装数据源 来执行自定义的SQL
}
