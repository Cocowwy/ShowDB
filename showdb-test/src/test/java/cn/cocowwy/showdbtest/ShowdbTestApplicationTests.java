package cn.cocowwy.showdbtest;

import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.config.ShowDbFactory;
import cn.cocowwy.showdbcore.strategy.MonitorExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlStructExecuteStrategy;
import cn.cocowwy.showdbcore.util.DataSourcePropUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
class ShowdbTestApplicationTests {
    @Autowired
    private MonitorExecuteStrategy monitorExecuteStrategy;

    @Test
    void contextLoads() {
    }

    @Test
    void changeDataSource() throws SQLException {
        System.out.println("当前数据源" + DataSourcePropUtil.getBeanName(ShowDbFactory.getJdbcTemplate().getDataSource()));
        GlobalContext.switchDataSource("slaveDataSource");
        System.out.println("切换后数据源" + DataSourcePropUtil.getBeanName(ShowDbFactory.getJdbcTemplate().getDataSource()));
    }

}
