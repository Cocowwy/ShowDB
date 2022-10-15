package cn.cocowwy.showdbtest;

import cn.cocowwy.showdbcore.strategy.SqlExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlMonitorExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlStructExecuteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${server.servlet.context-path}")
    private String context;
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
    }
}
