package cn.cocowwy.showdbautoconfigure.configuration;

import cn.cocowwy.showdbcore.config.ShowDbFactory;
import cn.cocowwy.showdbcore.exception.ShowDbException;
import cn.cocowwy.showdbcore.util.EndpointUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * ShowDB 自动配置类
 *
 * @author Cocowwy
 * @create 2022-03-03-22:13
 */
@ConditionalOnClass(DataSource.class)
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ShowDbProperties.class})
@ConditionalOnProperty(name = "showdb.enable", havingValue = "true")
public class ShowDbAutoConfiguration implements InitializingBean {

    private final DataSource dataSource;
    private final ShowDbProperties properties;

    public ShowDbAutoConfiguration(DataSource dataSource, ShowDbProperties properties) {
        if (Objects.isNull(dataSource)) {
            throw new ShowDbException("cant find datasource (bean) ,please config it and restart");
        }
        this.dataSource = dataSource;
        this.properties = properties;
    }

    @Override
    public void afterPropertiesSet() {
        ShowDbFactory.INSTANCE.init(dataSource);
        EndpointUtil.setEnableSet(properties.getEndpoint());
    }
}
