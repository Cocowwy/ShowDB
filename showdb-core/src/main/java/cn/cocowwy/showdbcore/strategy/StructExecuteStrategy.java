package cn.cocowwy.showdbcore.strategy;

import cn.cocowwy.showdbcore.entities.TableField;
import cn.cocowwy.showdbcore.entities.TableInfo;

import java.util.List;

/**
 * @author Cocowwy
 * @create 2022-03-03-20:05
 */
public interface StructExecuteStrategy extends SqlExecuteStrategy {
    /**
     * 表结构文档
     */
    List<TableField> tableStructure(String tableName);

    /**
     * 表名集合
     * @return
     */
    List<String> tableNames();

    /**
     * 获取建表语句
     * @param tableName
     * @return
     */
    String createTableStatement(String tableName);

    /**
     * 表详细信息
     * @return
     */
    TableInfo tableInfo(String table);
}
