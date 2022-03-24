package cn.cocowwy.showdbcore.entities;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-11:09
 */
public class TableStruct {
    /**
     * table_schema
     */
    private String schema;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 字段类型
     */
    private String type;
    /**
     * 是否主键
     */
    private Boolean pk;
    /**
     * 是否自增
     */
    private String autoIncrement;
    /**
     * 默认值
     */
    private String columnDefault;

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    /**
     * 可否为空
     */
    private Boolean nullable;
    /**
     * 字段描述
     */
    private String comment;

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getPk() {
        return pk;
    }

    public void setPk(Boolean pk) {
        this.pk = pk;
    }

    public String getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(String autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
