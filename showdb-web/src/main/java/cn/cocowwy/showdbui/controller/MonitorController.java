package cn.cocowwy.showdbui.controller;

import cn.cocowwy.showdbcore.annotation.Endpoint;
import cn.cocowwy.showdbcore.constants.EndpointEnum;
import cn.cocowwy.showdbcore.entities.IpCount;
import cn.cocowwy.showdbcore.entities.Res;
import cn.cocowwy.showdbui.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Cocowwy
 * @create 2022-03-03-19:01
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    /**
     * 数据库主从连接信息
     * @return
     */
    @Endpoint(EndpointEnum.MONITOR_MASTER_SLAVE)
    @GetMapping("/masterSlaveInfo")
    public Res<Void> masterSlaveInfo() {
        return null;
    }

    /**
     * 当前数据源 IP客户端连接数
     * @return
     */
    @Endpoint(EndpointEnum.MONITOR_DATABASE)
    @GetMapping("/ipCountInfo")
    public Res<List<IpCount>> ipCountInfo() {
        return Res.success(monitorService.ipCountInfo());
    }
}
