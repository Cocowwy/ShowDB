package cn.cocowwy.showdbcore.strategy;

import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.strategy.SqlExecuteStrategy;

/**
 * 抽象标记接口
 * 用于区分策略类执行的数据库类型
 * Marker interface to distinguish the type of database the policy class executes
 *
 * @author cocowwy.cn
 * @create 2022-03-03-16:40
 */
public abstract class MySqlExecuteStrategy implements SqlExecuteStrategy {
    @Override
    public DBEnum strategy() {
        return DBEnum.MySQL;
    }
}