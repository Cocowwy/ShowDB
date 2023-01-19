package cn.cocowwy.showdbcore.controller;

import cn.cocowwy.showdbcore.config.ShowDbCache;
import cn.cocowwy.showdbcore.config.ShowDBContext;
import cn.cocowwy.showdbcore.entities.DsInfo;
import cn.cocowwy.showdbcore.entities.Res;
import cn.cocowwy.showdbcore.entities.ShowDBConfig;
import cn.cocowwy.showdbcore.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
     * 获取连接数据源相关信息
     *
     * @return
     */
    @GetMapping("dsInfo")
    public Res<List<DsInfo>> getDsInfo() {
        return Res.success(configService.getDsInfo());
    }

    /**
     * 清除缓存
     *
     * @return
     */
    @DeleteMapping("cleanCache")
    public Res<Void> cleanCache() {
        ShowDbCache.clean();
        return Res.success();
    }

    /**
     * 自定义
     *
     * @return
     */
    @GetMapping("config")
    public Res<ShowDBConfig> config() {
        return Res.success(ShowDBContext.getShowDBConfig());
    }
}
