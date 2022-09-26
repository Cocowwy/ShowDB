package cn.cocowwy.showdbui.controller;

import cn.cocowwy.showdbcore.cache.ShowDbCache;
import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.entities.Customize;
import cn.cocowwy.showdbcore.entities.DsInfo;
import cn.cocowwy.showdbcore.entities.Res;
import cn.cocowwy.showdbui.annotation.ShowDBRestController;
import cn.cocowwy.showdbui.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * 配置相关
 *
 * @author Cocowwy
 * @create 2022-03-03-21:23
 */
@ShowDBRestController("/config")
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
    @GetMapping("customize")
    public Res<Customize> customize() {
        return Res.success(GlobalContext.getCustomize());
    }
}
