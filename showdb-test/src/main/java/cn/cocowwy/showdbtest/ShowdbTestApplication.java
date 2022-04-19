package cn.cocowwy.showdbtest;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class ShowdbTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowdbTestApplication.class, args);
    }

    //=========模拟多数据源测试============

    /**
     * 数据源 1 配置
     */
    @Bean(name = "cms", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.cms")
    @Primary
    public DruidDataSource createDataSource1Source() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 数据源 2 配置
     */
    @Bean(name = "oms", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.oms")
    public DruidDataSource createDataSource2Source() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 数据源 3 配置
     */
    @Bean(name = "pms", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.pms")
    public DruidDataSource createDataSource3Source() {
        return DruidDataSourceBuilder.create().build();
    }
}
