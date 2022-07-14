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
        //todo
        SlaveStatus mock = new SlaveStatus();
        mock.setMasterHost("3306");
        mock.setMasterPort("3306");
        mock.setMasterUser("3306");
        if (ds.equals("cms")) {
            return Res.success(info);
        }
        return Res.success(mock);
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
//        return Res.success(monitorService.transcationalStatus(ds));
        TranscationalStatus a = new TranscationalStatus();
        a.setTrxId("d2");
        a.setTrxAdaptiveHashLatched("d2xx");
        return Res.success(Arrays.asList(a));
    }
}
