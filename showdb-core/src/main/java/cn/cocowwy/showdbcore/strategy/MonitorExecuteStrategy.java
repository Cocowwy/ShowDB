package cn.cocowwy.showdbcore.strategy;

import cn.cocowwy.showdbcore.entities.IpCount;

import java.util.List;

/**
 * @author Cocowwy
 * @create 2022-03-03-20:07
 */
public interface MonitorExecuteStrategy extends SqlExecuteStrategy {
    /**
     * 查询每个IP所占用的连接数
     * @return
     */
    List<IpCount> ipConnectCount();
}
