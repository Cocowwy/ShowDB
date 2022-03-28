package cn.cocowwy.showdbui.controller;

import cn.cocowwy.showdbcore.annotation.Endpoint;
import cn.cocowwy.showdbcore.constants.EndpointEnum;
import cn.cocowwy.showdbcore.entities.Res;
import cn.cocowwy.showdbcore.entities.TableField;
import cn.cocowwy.showdbcore.entities.TableStructVo;
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
     * 获取表的结构-分页
     * Get the structure of all tables
     * @return
     */
    @Endpoint(EndpointEnum.STRUCTURE)
    @GetMapping("/{pageSize}/{pageNumber}")
    public Res<TableStructVo> tableStruct(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNumber") Integer pageNumber) {
        return Res.success(structService.tableStruct(pageSize, pageNumber));
    }

    /**
     * 表创建语句
     * Table creation statement
     * @param table 表名
     * @return
     */
    @Endpoint(EndpointEnum.STRUCTURE)
    @GetMapping("/create/{table}")
    public Res<String> tableCreateStatement(@PathVariable("table") String table) {
        return Res.success(structService.tableCreateStatement(table));
    }

    /**
     * 获取某表的结构
     * @param table
     * @return 表结构
     */
    @Endpoint(EndpointEnum.STRUCTURE)
    @GetMapping("/{pageSize}/{pageNumber}/{table}")
    public Res<TableStructVo> tableStruct(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNumber") Integer pageNumber, @PathVariable("table") String table) {
        return Res.success(structService.tableStruct(pageSize, pageNumber, table));
    }

    /**
     * 获取所有的表名称集合
     * @return
     */
    @GetMapping("/all")
    public Res<List<String>> tableNames() {
        return Res.success(structService.tableNames());
    }
}
