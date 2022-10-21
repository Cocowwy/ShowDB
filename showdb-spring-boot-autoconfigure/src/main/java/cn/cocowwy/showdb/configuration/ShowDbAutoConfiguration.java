package cn.cocowwy.showdb.configuration;

import cn.cocowwy.showdbcore.cache.ShowDbCache;
import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.config.ShowDbFactory;
import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.entities.ShowDBConfig;
import cn.cocowwy.showdbcore.exception.ShowDbException;
import cn.cocowwy.showdbcore.strategy.SqlExecuteStrategy;
import cn.cocowwy.showdbcore.util.DataSourcePropUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * ShowDB 自动配置类
 *
 * @author Cocowwy
 * @create 2022-03-03-22:13
 */
@ConditionalOnClass(DataSource.class)
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ShowDbProperties.class})
@ComponentScan(basePackages = {
        "cn.cocowwy.showdbcore.strategy.impl",
        "cn.cocowwy.showdbui.controller",
        "cn.cocowwy.showdbui.service",
        "cn.cocowwy.showdbui.config",
        "cn.cocowwy.showdbcore.generate"
})
@ConditionalOnProperty(name = "showdb.enable", havingValue = "true")
@AutoConfigureBefore(SqlExecuteStrategy.class)
public class ShowDbAutoConfiguration implements InitializingBean {
    private static final Log logger = LogFactory.getLog(ShowDbAutoConfiguration.class);

    public ShowDbAutoConfiguration(ShowDbProperties properties, ApplicationContext applicationContext) {
        List<String> dataSources = Arrays.asList(applicationContext.getBeanNamesForType(DataSource.class));
        if (CollectionUtils.isEmpty(dataSources)) {
            throw new ShowDbException("Can't find datasource (bean) ,please config it and restart");
        }
        Map<String, DataSource> dataSourcesMap = dataSources.stream()
                .collect(Collectors.toMap(Function.identity(), beanName -> (DataSource) applicationContext.getBean(beanName)));

        GlobalContext.setDataSourcesMap(dataSourcesMap);

        Map<String, DBEnum> dataSourcesTypeMap = dataSources.stream()
                .collect(Collectors.toMap(Function.identity(), DataSourcePropUtil::dataSourceTypeByBeanName));
        GlobalContext.setDataSourcesTypeMap(dataSourcesTypeMap);

        ShowDbFactory.INSTANCE.init(applicationContext);

        // 缓存定时清理器
        ShowDbCache.addCachaTask(properties.getRefresh());

        // 配置信息
        GlobalContext.setShowDBConfig(buildShowDBConfig(properties));
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
        Plugin plugin = Optional.ofNullable(showDbProperties.getPlugin()).orElse(new Plugin());
        ShowDBConfig.Plugin plg = new ShowDBConfig.Plugin();
        copyProperties(plugin, plg);

        // customize
        ShowDBConfig.Customize cst = new ShowDBConfig.Customize();
        if (Objects.nonNull(showDbProperties.getCustomize())) {
            copyProperties(showDbProperties.getCustomize(), cst);
        }
        return new ShowDBConfig(cst, plg);
    }

    private void bannerLog() {
        logger.info("ShowDB started successfully!" +
                "\nCreateBy: Cocowwy" +
                "\nGithub地址：https://github.com/Cocowwy/ShowDB");
    }
}
