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

    //=========多数据源测试============

    /**
     * 主库配置
     */
    @Bean(name = "masterDataSource", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Primary
    public DruidDataSource createMasterSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 从库配置
     */
    @Bean(name = "slaveDataSource", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DruidDataSource createSlaveSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
