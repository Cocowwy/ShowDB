package cn.cocowwy.showdbtest;

import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.config.ShowDbFactory;
import cn.cocowwy.showdbcore.strategy.SqlExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlMonitorExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlStructExecuteStrategy;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-10:35
 */
@Component
public class Runner implements ApplicationRunner {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private List<DataSource> dataSource;
    @Autowired
    private List<SqlExecuteStrategy> strategy;
    @Autowired
    private MySqlStructExecuteStrategy mySqlStructExecuteStrategy;
    @Autowired
    private MySqlMonitorExecuteStrategy mySqlMonitorExecuteStrategy;
    @Autowired
    private List<MySqlExecuteStrategy> mySqlExecuteStrategies;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 测试基础信息
        test1();

        // 测试事务查询
        test2();

        System.out.println("http://localhost/db");
    }

    @Transactional(rollbackFor = Exception.class)
    public void test2() throws InterruptedException, SQLException {
//        System.out.println( "beagin transactional");
//        DataSource oms = GlobalContext.getDataSourcesMap().get("oms");
//        JdbcTemplate template = new JdbcTemplate(oms);
//
//
//        List<Object> query = template.query("select * from oms.oms_cart_item", (rw, o) -> null);
//
//        template.execute("delete from oms.oms_cart_item where id = 13");
//
//
//        Connection conn = template.getDataSource().getConnection();
//        conn.getAutoCommit();
//        conn.setAutoCommit(false);
//        conn.commit();
//        // 30m 的长事务
//        Thread.sleep(30000L);
//
//        throw new RuntimeException("test");
    }

    private void test1() throws SQLException {
//        System.out.println("已注入策略：" + strategy.size());
//        System.out.println("当前数据库源：" + GlobalContext.getDatabaseProductName());
//        System.out.println("当前版本：" + GlobalContext.getDatabaseProductVersion());
////        System.out.println("JDBCTemplate:"+ShowDbFactory.getJdbcTemplate());
//        mySqlStructExecuteStrategy.tableNames().forEach(System.out::println);
//        System.out.println("表集合");
//
//        System.out.println("建表语句：" + mySqlStructExecuteStrategy.createTableStatement("user_1"));
//        System.out.println(mySqlMonitorExecuteStrategy.ipConnectCount());
////        测试数据源切换
//        System.out.println(ShowDbFactory.getPresentDatasourceName());
//        DataSource dataSource1 = ShowDbFactory.getDataSource();
//        ShowDbFactory.switchDataSource("slaveDataSource");
//        System.out.println(ShowDbFactory.getPresentDatasourceName());
//        DataSource dataSource2 = ShowDbFactory.getDataSource();
//        System.out.println("测试数据源切换");
//        ;
//        System.out.println("MySQL执行策略类：" + mySqlExecuteStrategies.size());
//        mySqlMonitorExecuteStrategy.ipConnectCount();

    }


}
