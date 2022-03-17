package cn.cocowwy.showdbui.controller;

import cn.cocowwy.showdbcore.annotation.Endpoint;
import cn.cocowwy.showdbcore.constants.EndpointEnum;
import cn.cocowwy.showdbcore.entities.Res;
import cn.cocowwy.showdbcore.entities.TableStruct;
import cn.cocowwy.showdbui.service.StructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Cocowwy
 * @create 2022-03-03-18:58
 */
@RestController("/showdb/struct")
public class StructController {
    @Autowired
    private StructService structService;

    /**
     * 获取所有表的结构
     * Get the structure of all tables
     * @return
     */
    @Endpoint(EndpointEnum.STRUCTURE)
    @GetMapping("/all")
    public Res<List<TableStruct>> allTableStruct() {
        return Res.success(structService.allTableStruct());
    }
}
