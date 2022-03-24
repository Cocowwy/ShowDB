package cn.cocowwy.showdbcore.strategy.impl.mysql;

import cn.cocowwy.showdbcore.config.ShowDbFactory;
import cn.cocowwy.showdbcore.entities.TableStruct;
import cn.cocowwy.showdbcore.strategy.StructExecuteStrategy;
import cn.cocowwy.showdbcore.util.DataSourcePropUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cocowwy
 * @create 2022-03-03-21:57
 */
@Component
public class MySqlStructExecuteStrategy implements StructExecuteStrategy, MySqlExecuteStrategy {
    private static final Map<DataSource,String> mapSchema = new HashMap<>(1);

    /**
     * 表结构
     * @param tableName
     */
    @Override
    public List<TableStruct> tableStructure(String tableName) {
        return ShowDbFactory.getJdbcTemplate()
                .query(String.format("SELECT TABLE_SCHEMA,\n" +
                                "       TABLE_NAME,\n" +
                                "       COLUMN_NAME,\n" +
                                "       COLUMN_TYPE,\n" +
                                "       IS_NULLABLE,\n" +
                                "       COLUMN_DEFAULT,\n" +
                                "       COLUMN_COMMENT,\n" +
                                "       COLUMN_KEY\n" +
                                "FROM information_schema.COLUMNS \n" +
                                "WHERE table_name = '%s' AND TABLE_SCHEMA = '%s' ORDER BY ORDINAL_POSITION ASC", tableName,
                        DataSourcePropUtil.getMysqlSchemaFromDataSource()),
                        (rs, i) -> {
                            TableStruct ts = new TableStruct();
                            ts.setSchema(rs.getString("TABLE_SCHEMA"));
                            ts.setTableName(rs.getString("TABLE_NAME"));
                            ts.setFieldName(rs.getString("COLUMN_NAME"));
                            ts.setType(rs.getString("COLUMN_TYPE"));
                            ts.setNullable(rs.getString("IS_NULLABLE").equals("YES")
                                    ? Boolean.TRUE : Boolean.FALSE);
                            ts.setColumnDefault(rs.getString("COLUMN_DEFAULT"));
                            ts.setComment(rs.getString("COLUMN_COMMENT"));
                            ts.setPk(rs.getString("COLUMN_KEY").equals("PRI")
                                    ? Boolean.TRUE : Boolean.FALSE);
                            return ts;
                        });
    }

    /**
     * 表名集合
     * @return
     */
    @Override
    public List<String> tableNames() {
        return ShowDbFactory.getJdbcTemplate()
                .query("show tables", (rs, i) ->
                        rs.getObject(1, String.class));
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
