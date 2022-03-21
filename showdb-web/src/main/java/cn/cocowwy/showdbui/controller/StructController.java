package cn.cocowwy.showdbui.controller;

import cn.cocowwy.showdbcore.annotation.Endpoint;
import cn.cocowwy.showdbcore.constants.EndpointEnum;
import cn.cocowwy.showdbcore.entities.Res;
import cn.cocowwy.showdbcore.entities.TableStruct;
import cn.cocowwy.showdbui.service.StructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Cocowwy
 * @create 2022-03-03-18:58
 */
@RestController
@RequestMapping("/showdb/struct")
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
    public Res<List<List<TableStruct>>> allTableStruct() {
        return Res.success(structService.allTableStruct());
    }

    /**
     * 表创建语句
     * Table creation statement
     * @param table 表名
     * @return
     */
    @Endpoint(EndpointEnum.STRUCTURE)
    @GetMapping("/{table}")
    public Res<String> tableCreateStatement(@PathVariable("table")String table) {
        return Res.success(structService.tableCreateStatement(table));
    }
}
