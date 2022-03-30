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
    @GetMapping("/{ds}/switchDataSource/{name}")
    @Deprecated
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
    @GetMapping("/{ds}/osEnv")
    public Res<String> getOsEnv(@RequestParam("ds") String ds) {
        return Res.success(configService.getOsEnv(ds));
    }

    /**
     * DB版本号
     * @return
     */
    @GetMapping("/{ds}/dbVersion")
    public Res<String> getDbVersion(@RequestParam("ds") String ds) {
        return Res.success(configService.getDbVersion(ds));
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
