package cn.cocowwy.showdbcore.entities;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-16:20
 */
public class TableInfo {
    private String tableName;
    private String tableComment;

    //detail
    private String engine;
    private String rows;
    private String avgRowLength;
    private String dataSize;
    private String maxDataLenth;
    private String indexSie;
    private String createTime;
    private String updateTime;
    private String collation;

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

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getAvgRowLength() {
        return avgRowLength;
    }

    public void setAvgRowLength(String avgRowLength) {
        this.avgRowLength = avgRowLength;
    }

    public String getDataSize() {
        return dataSize;
    }

    public void setDataSize(String dataSize) {
        this.dataSize = dataSize;
    }

    public String getMaxDataLenth() {
        return maxDataLenth;
    }

    public void setMaxDataLenth(String maxDataLenth) {
        this.maxDataLenth = maxDataLenth;
    }

    public String getIndexSie() {
        return indexSie;
    }

    public void setIndexSie(String indexSie) {
        this.indexSie = indexSie;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCollation() {
        return collation;
    }

    public void setCollation(String collation) {
        this.collation = collation;
    }
}
