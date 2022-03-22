package cn.cocowwy.showdbui.controller;

import cn.cocowwy.showdbcore.entities.DsInfo;
import cn.cocowwy.showdbcore.entities.Res;
import cn.cocowwy.showdbui.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 配置相关
 *
 * @author Cocowwy
 * @create 2022-03-03-21:23
 */
@RestController
@RequestMapping("/showdb/config")
public class ConfigController {
    @Autowired
    private ConfigService configService;

    /**
     * DB所处环境
     * @return
     */
    @GetMapping("/osEnv")
    public Res<String> getOsEnv() {
        return Res.success(configService.getOsEnv());
    }

    /**
     * DB版本号
     * @return
     */
    @GetMapping("/dbVersion")
    public Res<String> getDbVersion() {
        return Res.success(configService.getDbVersion());
    }

    /**
     * 获取连接数据源相关信息
     * @return
     */
    @GetMapping("/dsInfo")
    public Res<List<DsInfo>> getDsInfo() {
        return Res.success(configService.getDsInfo());
    }
}
