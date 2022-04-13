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
     * @param ds 数据源
     * @return IP连接信息
     */
    List<IpCount> ipConnectCount(String ds);

    /**
     * 主从信息查询，当前支持MySQL
     *  - MySQL
     * @param ds 数据源
     * @return 主从状态
     */
    SlaveStatus slaveStatus(String ds);

    /**
     * 数据size相关信息
     * @param ds 数据源
     * @return 数据size相关信息
     */
    DsInfo dsInfo(String ds);

    /**
     * 查询当前运行事务
     * @param ds 数据源
     * @return 当前事务详情
     */
    List<TranscationalStatus> transcationalStatus(String ds);
}
