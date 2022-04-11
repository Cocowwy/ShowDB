package cn.cocowwy.showdbcore.strategy;

import cn.cocowwy.showdbcore.entities.DsInfo;
import cn.cocowwy.showdbcore.entities.IpCount;
import cn.cocowwy.showdbcore.entities.SlaveStatus;
import cn.cocowwy.showdbcore.entities.TranscationalStatus;

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
    List<IpCount> ipConnectCount(String ds);

    /**
     * 主从信息查询，当前支持MySQL
     *  - MySQL
     * @return
     */
    SlaveStatus slaveStatus(String ds);

    /**
     * 数据库大小
     * @return
     */
    DsInfo dsInfo(String ds);

    /**
     * 查询当前运行事务
     * @param ds
     */
    List<TranscationalStatus> transcationalStatus(String ds);
}
