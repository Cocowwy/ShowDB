package cn.cocowwy.showdbcore.strategy.impl.mysql;

import cn.cocowwy.showdbcore.config.ShowDbFactory;
import cn.cocowwy.showdbcore.entities.TableField;
import cn.cocowwy.showdbcore.entities.TableInfo;
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
    private static final Map<DataSource, String> mapSchema = new HashMap<>(1);

    /**
     * 表结构
     * @param tableName
     */
    @Override
    public List<TableField> tableStructure(String ds, String tableName) {
        return ShowDbFactory.getJdbcTemplate(ds)
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
                        DataSourcePropUtil.getMysqlSchemaFromDataSourceBeanName(ds)),
                        (rs, i) -> {
                            TableField ts = new TableField();
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
    public List<String> tableNames(String ds) {
        return ShowDbFactory.getJdbcTemplate(ds)
                .query("show tables", (rs, i) ->
                        rs.getObject(1, String.class));
    }

    /**
     * 获取建表语句
     * @param tableName
     * @return
     */
    @Override
    public String createTableStatement(String ds, String tableName) {
        List<String> result = ShowDbFactory.getJdbcTemplate(ds)
                .query(String.format("show create table %s", tableName), (resultSet, i) ->
                        resultSet.getObject(2, String.class));
        return CollectionUtils.lastElement(result);
    }

    /**
     * 表描述信息
     * @return
     */
    @Override
    public TableInfo tableComment(String ds, String table) {
        List<TableInfo> rts = ShowDbFactory.getJdbcTemplate(ds)
                .query(String.format("SELECT TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES  " +
                                "WHERE TABLE_NAME = '%s' AND TABLE_SCHEMA = '%s'", table,
                        DataSourcePropUtil.getMysqlSchemaFromDataSourceBeanName(ds)),
                        (rs, i) -> {
                            TableInfo ti = new TableInfo();
                            ti.setTableComment(rs.getString("TABLE_COMMENT"));
                            return ti;
                        });

        return CollectionUtils.firstElement(rts);
    }

    /**
     * 表详细信息
     * @return
     * @param ds
     * @param table
     */
    @Override
    public TableInfo tableInfo(String ds, String table) {
        String sql = "show table status from %s where name = '%s'";
        List<TableInfo> rts = ShowDbFactory.getJdbcTemplate(ds)
                .query(String.format(sql, DataSourcePropUtil.getMysqlSchemaFromDataSourceBeanName(ds), table),
                        (rs, i) -> {
                            TableInfo ti = new TableInfo();
                            ti.setTableName(rs.getString("Name"));
                            ti.setEngine(rs.getString("Engine"));
                            ti.setRows(rs.getString("Rows"));
                            ti.setAvgRowLength(rs.getString("Avg_row_length"));
                            ti.setDataSize(rs.getString("Data_length"));
                            ti.setIndexSie(rs.getString("Index_length"));
                            ti.setMaxDataLenth(rs.getString("Max_data_length"));
                            ti.setCreateTime(rs.getString("Create_time"));
                            ti.setUpdateTime(rs.getString("Update_time"));
                            ti.setCollation(rs.getString("Collation"));
                            ti.setTableComment(rs.getString("Comment"));
                            return ti;
                        });

        return CollectionUtils.lastElement(rts);
    }


}
