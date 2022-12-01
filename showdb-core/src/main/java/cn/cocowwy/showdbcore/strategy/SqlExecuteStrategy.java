package cn.cocowwy.showdbcore.strategy;

import cn.cocowwy.showdbcore.constants.DBEnum;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-19:33
 */
public interface SqlExecuteStrategy {
    DBEnum strategy();
}
