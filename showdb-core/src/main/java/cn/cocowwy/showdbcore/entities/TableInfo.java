package cn.cocowwy.showdbcore.entities;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-16:20
 */
public class TableInfo {
    private String tableName;
    private String tableComment;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
}
