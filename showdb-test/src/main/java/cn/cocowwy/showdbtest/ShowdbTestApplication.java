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
     * 数据源1配置
     */
    @Bean(name = "sexytea_dev", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.data-source1")
    @Primary
    public DruidDataSource createDataSource1Source() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 数据源2配置
     */
    @Bean(name = "sexytea_branch", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.data-source2")
    public DruidDataSource createDataSource2Source() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 数据源3配置
     */
    @Bean(name = "sexytea_cms", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.data-source3")
    public DruidDataSource createDataSource3Source() {
        return DruidDataSourceBuilder.create().build();
    }
}
