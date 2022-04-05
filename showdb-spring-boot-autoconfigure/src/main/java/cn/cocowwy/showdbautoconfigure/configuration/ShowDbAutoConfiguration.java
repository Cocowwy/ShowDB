package cn.cocowwy.showdbautoconfigure.configuration;

import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.config.ShowDbFactory;
import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.exception.ShowDbException;
import cn.cocowwy.showdbcore.strategy.SqlExecuteStrategy;
import cn.cocowwy.showdbcore.util.DataSourcePropUtil;
import cn.cocowwy.showdbcore.util.EndpointUtil;
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
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        "cn.cocowwy.showdbcore.aspect"
})
@ConditionalOnProperty(name = "showdb.enable", havingValue = "true")
@AutoConfigureBefore(SqlExecuteStrategy.class)
public class ShowDbAutoConfiguration implements InitializingBean {
    private static final Log logger = LogFactory.getLog(ShowDbAutoConfiguration.class);
    private final DataSource dataSource;
    private final ShowDbProperties properties;

    public ShowDbAutoConfiguration(ShowDbProperties properties, ApplicationContext applicationContext) {
        List<String> dataSources = Arrays.asList(applicationContext.getBeanNamesForType(DataSource.class));
        if (CollectionUtils.isEmpty(dataSources)) {
            throw new ShowDbException("cant find datasource (bean) ,please config it and restart");
        }
        String datasourceName = CollectionUtils.firstElement(dataSources);
        // Inject default data source
        this.dataSource = (DataSource) applicationContext.getBean(datasourceName);
        this.properties = properties;
        Map<String, DataSource> dataSourcesMap = dataSources.stream()
                .collect(Collectors.toMap(Function.identity(), beanName -> (DataSource) applicationContext.getBean(beanName)));

        GlobalContext.setDataSourcesMap(dataSourcesMap);

        Map<String, DBEnum> dataSourcesTypeMap = dataSources.stream()
                .collect(Collectors.toMap(Function.identity(), DataSourcePropUtil::dataSourceTypeByBeanName));
        GlobalContext.setDataSourcesTypeMap(dataSourcesTypeMap);

        ShowDbFactory.INSTANCE.init();
        bannerLog();
    }

    @Override
    public void afterPropertiesSet() throws SQLException {
        EndpointUtil.setEnableSet(properties.getEndpoint());
    }

    private void bannerLog() {
        logger.info("\n" +
                "███████╗██╗  ██╗ ██████╗ ██╗    ██╗██████╗ ██████╗\n" +
                "██╔════╝██║  ██║██╔═══██╗██║    ██║██╔══██╗██╔══██╗\n" +
                "███████╗███████║██║   ██║██║ █╗ ██║██║  ██║██████╔╝\n" +
                "╚════██║██╔══██║██║   ██║██║███╗██║██║  ██║██╔══██╗\n" +
                "███████║██║  ██║╚██████╔╝╚███╔███╔╝██████╔╝██████╔╝\n" +
                "╚══════╝╚═╝  ╚═╝ ╚═════╝  ╚══╝╚══╝ ╚═════╝ ╚═════╝" +
                "\nCreateBy：Cocowwy" +
                "\nGit地址：https://github.com/Cocowwy/ShowDB" +
                "\nShowDB 启动成功！");
    }
}
