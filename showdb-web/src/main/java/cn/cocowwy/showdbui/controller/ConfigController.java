package cn.cocowwy.showdbui.controller;

import cn.cocowwy.showdbcore.cache.ShowDbCache;
import cn.cocowwy.showdbcore.entities.DsInfo;
import cn.cocowwy.showdbcore.entities.Res;
import cn.cocowwy.showdbcore.exception.ErrorDefinition;
import cn.cocowwy.showdbui.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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
     * 修改当前数据源
     * @return
     */
    @GetMapping("/switchDataSource/{name}")
    public Res<Boolean> switchDataSource(@PathVariable("name") String name) {
        try {
            configService.switchDataSource(name);
        } catch (SQLException throwables) {
            return Res.error(ErrorDefinition.SWITCH_DATA_SOURCE_ERROR);
        }
        return Res.success(Boolean.TRUE);
    }

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

    @DeleteMapping("/cleanCache")
    public Res<Void> cleanCache() {
        ShowDbCache.clean();
        return Res.success();
    }
}
