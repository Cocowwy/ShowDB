package cn.cocowwy.showdbcore.strategy;

import cn.cocowwy.showdbcore.entities.IpCount;
import cn.cocowwy.showdbcore.entities.SlaveStatus;

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

    /**
     * 主从信息查询，当前支持MySQL
     *  - MySQL
     * @return
     */
    SlaveStatus slaveStatus();
}
