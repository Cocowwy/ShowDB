package cn.cocowwy.showdbui.controller;

import cn.cocowwy.showdbcore.annotation.Endpoint;
import cn.cocowwy.showdbcore.constants.EndpointEnum;
import cn.cocowwy.showdbcore.entities.IpCount;
import cn.cocowwy.showdbcore.entities.Res;
import cn.cocowwy.showdbcore.entities.SlaveStatus;
import cn.cocowwy.showdbcore.entities.TranscationalStatus;
import cn.cocowwy.showdbui.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Cocowwy
 * @create 2022-03-03-19:01
 */
@RestController
@RequestMapping("showdb/monitor/{ds}")
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    /**
     * 数据库主从连接信息
     * - MySQL 仅开启主从之后有返回值
     *
     * @return
     */
    @Endpoint(EndpointEnum.MONITOR_MASTER_SLAVE)
    @GetMapping("masterSlaveInfo")
    public Res<SlaveStatus> masterSlaveInfo(@PathVariable("ds") String ds) {
        SlaveStatus info = monitorService.masterSlaveInfo(ds);
        return Res.success(info);
        //----------mock--------------
//        if (ds.equals("oms")) {
//            SlaveStatus mock = new SlaveStatus();
//            mock.setSlaveIOState("Waiting for master to send event");
//            mock.setMasterHost("localhost");
//            mock.setMasterUser("photorepl");
//            mock.setMasterPort("3301");
//            mock.setMasterRetryCount("86400");
//            mock.setSlaveIORunning("Yes");
//            mock.setSlaveSQLRunning("NO");
//            mock.setSqlDelay("0");
//            mock.setRelayLogFile("mysqld-relay-bin.005201");
//            mock.setMasterLogFile("mysql-bin.001822");
//            return Res.success(info);
//        }
        //------------mock--------------
    }

    /**
     * 当前数据源所对应数据库的 IP客户端连接数
     *
     * @return
     */
    @Endpoint(EndpointEnum.MONITOR_IP_CONNECTION)
    @GetMapping("ipCountInfo")
    public Res<List<IpCount>> ipCountInfo(@PathVariable("ds") String ds) {
        return Res.success(monitorService.ipCountInfo(ds));
    }

    /**
     * 事务查询
     *
     * @param ds
     * @return
     */
    @Endpoint(EndpointEnum.MONITOR_PERFORMANCE)
    @GetMapping("trxInfo")
    public Res<List<TranscationalStatus>> trxInfo(@PathVariable("ds") String ds) {
        return Res.success(monitorService.transcationalStatus(ds));
    }
}
