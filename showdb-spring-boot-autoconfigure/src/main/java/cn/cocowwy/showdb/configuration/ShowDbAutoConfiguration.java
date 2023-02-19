package cn.cocowwy.showdb.configuration;

import cn.cocowwy.showdbcore.config.ShowDbCache;
import cn.cocowwy.showdbcore.config.ShowDBContext;
import cn.cocowwy.showdbcore.entities.ShowDBConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.*;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * ShowDB 自动配置类
 *
 * @author Cocowwy
 * @create 2022-03-03-22:13
 */
@ConditionalOnClass(DataSource.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ShowDbProperties.class})
@ComponentScan(basePackages = {
        "cn.cocowwy.showdbcore.strategy.impl",
        "cn.cocowwy.showdbcore.controller",
        "cn.cocowwy.showdbcore.service",
        "cn.cocowwy.showdbcore.config",
        "cn.cocowwy.showdbcore.generate"
})
@ConditionalOnProperty(name = "showdb.enable", havingValue = "true")
@ConditionalOnWebApplication
public class ShowDbAutoConfiguration implements InitializingBean {
    private static final Log logger = LogFactory.getLog(ShowDbAutoConfiguration.class);

    public ShowDbAutoConfiguration(ShowDbProperties properties, ApplicationContext applicationContext) {
        // initialize
        ShowDBContext.initialize(applicationContext);

        // 缓存定时清理器
        ShowDbCache.addCacheTask(properties.getRefresh());

        // 配置信息
        ShowDBContext.setShowDBConfig(buildShowDBConfig(properties));
    }

    @Override
    public void afterPropertiesSet() {
        bannerLog();
    }

    /**
     * ShowDB的配置信息
     * @param showDbProperties showDbProperties
     * @return ShowDBConfig
     */
    public ShowDBConfig buildShowDBConfig(ShowDbProperties showDbProperties) {
        ShowDBConfig.Plugin plugin = Optional.ofNullable(showDbProperties.getPlugin()).orElse(new ShowDBConfig.Plugin());
        ShowDBConfig.Plugin plg = new ShowDBConfig.Plugin();
        copyProperties(plugin, plg);

        // customize
        if (showDbProperties.getCustomize() == null) {
            return new ShowDBConfig(null, plg);
        } else {
            ShowDBConfig.Customize cst = new ShowDBConfig.Customize();
            copyProperties(showDbProperties.getCustomize(), cst);
            return new ShowDBConfig(cst, plg);
        }
    }

    private void bannerLog() {
        logger.info("ShowDB started successfully!" +
                "\nCreateBy: Cocowwy" +
                "\nGithub地址：https://github.com/Cocowwy/ShowDB");
    }
}
