package cn.cocowwy.showdbcore.entities;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-11:09
 */
public class TableStruct {
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
    private Boolean autoIncrement;
    /**
     * 可否为空
     */
    private Boolean nullable;
    /**
     * 字段描述
     */
    private String comment;
}
