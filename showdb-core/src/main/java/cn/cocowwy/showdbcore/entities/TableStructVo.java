package cn.cocowwy.showdbcore.entities;

import java.util.List;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-15:20
 */
public class TableStructVo {
    private Integer total;

    private List<TableStruct> tableStructs;

    private TableInfo tableInfo;

    public static class TableStruct {
        private List<TableField> tableFieldList;

        private TableInfo tableInfo;

        public List<TableField> getTableFieldList() {
            return tableFieldList;
        }

        public void setTableFieldList(List<TableField> tableFieldList) {
            this.tableFieldList = tableFieldList;
        }

        public TableInfo getTableInfo() {
            return tableInfo;
        }

        public void setTableInfo(TableInfo tableInfo) {
            this.tableInfo = tableInfo;
        }
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<TableStruct> getTableStructs() {
        return tableStructs;
    }

    public void setTableStructs(List<TableStruct> tableStructs) {
        this.tableStructs = tableStructs;
    }

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }
}
