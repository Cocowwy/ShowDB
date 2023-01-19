package cn.cocowwy.showdbcore.controller;

import cn.cocowwy.showdbcore.entities.MyBatisGenerateDefinition;
import cn.cocowwy.showdbcore.entities.Res;
import cn.cocowwy.showdbcore.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@RestController
@RequestMapping("/showdb/generate")
public class GenerateController {
    @Autowired
    private GenerateService generateService;

    @GetMapping("/defind/mybatis/{ds}/{tableName}")
    public Res<MyBatisGenerateDefinition> defindConfigRes(@PathVariable("ds") String ds, @PathVariable("tableName") String tableName) {
        return Res.success(generateService.defineConfig(ds, tableName));
    }

    @PostMapping("/mybatis/{ds}")
    public Res<Boolean> mybatis(@RequestBody MyBatisGenerateDefinition generateDefind, @PathVariable("ds") String ds) {
        return Res.success(generateService.mybatis(generateDefind, ds));
    }
}
