package cn.cocowwy.showdbcore.strategy;

/**
 * @author Cocowwy
 * @create 2022-03-03-20:05
 */
public interface StructExecuteStrategy extends SqlExecuteStrategy {
    /**
     * 所有表结构文档
     */
    void tablesDoc();
}
