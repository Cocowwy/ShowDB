package cn.cocowwy.showdbui.controller;

import cn.cocowwy.showdbcore.annotation.Endpoint;
import cn.cocowwy.showdbcore.constants.EndpointEnum;
import cn.cocowwy.showdbcore.entities.IpCount;
import cn.cocowwy.showdbcore.entities.Res;
import cn.cocowwy.showdbcore.entities.SlaveStatus;
import cn.cocowwy.showdbui.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Cocowwy
 * @create 2022-03-03-19:01
 */
@RestController
@RequestMapping("/showdb/monitor/{ds}")
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    /**
     * 数据库主从连接信息
     *  -MySQL 仅开启主从之后有返回值
     * @return
     */
    @Endpoint(EndpointEnum.MONITOR_MASTER_SLAVE)
    @GetMapping("/masterSlaveInfo")
    public Res<SlaveStatus> masterSlaveInfo(@PathVariable("ds") String ds) {
        SlaveStatus info = monitorService.masterSlaveInfo(ds);
        return Res.success(info);
    }

    /**
     * 当前数据源所对应数据库的 IP客户端连接数
     * @return
     */
    @Endpoint(EndpointEnum.MONITOR_IP_CONNECTION)
    @GetMapping("/ipCountInfo")
    public Res<List<IpCount>> ipCountInfo(@PathVariable("ds") String ds) {
        return Res.success(monitorService.ipCountInfo(ds));
    }
}

