package cn.cocowwy.showdbtest;

import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.strategy.SqlExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlMonitorExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlStructExecuteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
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
    private DataSource dataSource;
    @Autowired
    private List<SqlExecuteStrategy> strategy;
    @Autowired
    private MySqlStructExecuteStrategy mySqlStructExecuteStrategy;
    @Autowired
    private MySqlMonitorExecuteStrategy mySqlMonitorExecuteStrategy;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("已注入策略：" + strategy.size());
        System.out.println("当前数据库源：" + GlobalContext.getDatabaseProductName());
        System.out.println("当前版本：" + GlobalContext.getDatabaseProductVersion());
//        System.out.println("JDBCTemplate:"+ShowDbFactory.getJdbcTemplate());
        mySqlStructExecuteStrategy.tableNames().forEach(System.out::println);
        System.out.println("表集合");

        System.out.println("建表语句：" + mySqlStructExecuteStrategy.createTableStatement("user_1"));
        System.out.println(mySqlMonitorExecuteStrategy.ipConnectCount());
    }
}
