package cn.cocowwy.showdbcore.strategy.impl.mysql;

import cn.cocowwy.showdbcore.config.ShowDbFactory;
import cn.cocowwy.showdbcore.entities.TableStruct;
import cn.cocowwy.showdbcore.strategy.StructExecuteStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Cocowwy
 * @create 2022-03-03-21:57
 */
@Component
public class MySqlStructExecuteStrategy implements StructExecuteStrategy, MySqlExecuteStrategy {
    /**
     * 表结构
     * @param tableName
     */
    @Override
    public TableStruct tableStructure(String tableName) {
        return null;
    }

    /**
     * 表名集合
     * @return
     */
    @Override
    public List<String> tableNames() {
        List<String> showTables = ShowDbFactory.getJdbcTemplate()
                .query("show tables", (rs, i) ->
                        rs.getObject(1, String.class));
        return showTables;
    }

    /**
     * 获取建表语句
     * @param tableName
     * @return
     */
    @Override
    public String createTableStatement(String tableName) {
        List<String> result = ShowDbFactory.getJdbcTemplate()
                .query(String.format("show create table %s", tableName), (resultSet, i) ->
                        resultSet.getObject(2, String.class));
        return CollectionUtils.lastElement(result);
    }
}
