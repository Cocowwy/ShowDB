package cn.cocowwy.showdbui.controller;

import cn.cocowwy.showdbcore.annotation.Endpoint;
import cn.cocowwy.showdbcore.constants.EndpointEnum;
import cn.cocowwy.showdbcore.entities.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cocowwy
 * @create 2022-03-03-19:01
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Endpoint(EndpointEnum.MONITOR_MASTER_SLAVE)
    @GetMapping("/masterSlaveInfo")
    public Result<Void> masterSlaveInfo() {
        return null;
    }
}

