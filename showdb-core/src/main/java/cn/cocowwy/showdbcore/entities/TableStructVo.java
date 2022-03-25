package cn.cocowwy.showdbcore.entities;

import java.util.List;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-15:20
 */
public class TableStructVo {
    private List<TableField> tableFieldList;

    private Integer total;

    private TableInfo tableInfo;

    public List<TableField> getTableFieldList() {
        return tableFieldList;
    }

    public void setTableFieldList(List<TableField> tableFieldList) {
        this.tableFieldList = tableFieldList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }
}
