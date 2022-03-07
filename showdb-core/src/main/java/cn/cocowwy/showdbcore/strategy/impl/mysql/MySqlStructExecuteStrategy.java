package cn.cocowwy.showdbcore.strategy.impl.mysql;

import cn.cocowwy.showdbcore.strategy.StructExecuteStrategy;
import org.springframework.stereotype.Component;

/**
 * @author Cocowwy
 * @create 2022-03-03-21:57
 */
@Component
public class MySqlStructExecuteStrategy implements StructExecuteStrategy {

    /**
     * 所有表结构文档
     */
    public void tablesDoc() {
        jdbcTemplate.execute("");
    }
}
