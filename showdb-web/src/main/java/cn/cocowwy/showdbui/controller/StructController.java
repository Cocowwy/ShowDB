package cn.cocowwy.showdbui.controller;

import cn.cocowwy.showdbcore.entities.Res;
import cn.cocowwy.showdbcore.entities.TableStructVo;
import cn.cocowwy.showdbui.service.StructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Cocowwy
 * @create 2022-03-03-18:58
 */
@RestController
@RequestMapping("showdb/struct")
public class StructController {
    @Autowired
    private StructService structService;

    /**
     * 获取表的结构-分页
     * Get the structure of all tables
     * @return
     */
    @GetMapping("{ds}/{pageSize}/{pageNumber}")
    public Res<TableStructVo> tableStruct(@PathVariable("ds") String ds,
                                          @PathVariable("pageSize") Integer pageSize,
                                          @PathVariable("pageNumber") Integer pageNumber) {
        return Res.success(structService.tableStruct(ds, pageSize, pageNumber));
    }

    /**
     * 表创建语句
     * Table creation statement
     * @param table 表名
     * @return
     */
    @GetMapping("{ds}/create/{table}")
    public Res<String> tableCreateStatement(@PathVariable("ds") String ds, @PathVariable("table") String table) {
        return Res.success(structService.tableCreateStatement(ds, table));
    }

    /**
     * Java 实体类代码生成
     *
     * @return
     */
    @GetMapping("{ds}/java/{table}")
    public Res<String> tableJavaCode(@PathVariable("ds") String ds, @PathVariable("table") String table) {
        return Res.success(structService.tableJavaCode(ds, table));
    }

    /**
     * 获取某表的结构
     * @param table
     * @return 表结构
     */
    @GetMapping("{ds}/{pageSize}/{pageNumber}/{table}")
    public Res<TableStructVo> tableStruct(@PathVariable("ds") String ds, @PathVariable("pageSize") Integer pageSize, @PathVariable("pageNumber") Integer pageNumber, @PathVariable("table") String table) {
        return Res.success(structService.tableStruct(ds, pageSize, pageNumber, table));
    }

    /**
     * 获取某张表的详细信息
     * @param table
     * @return 表结构
     */
    @GetMapping("{ds}/{table}/detailInfo")
    public Res<TableStructVo> tableDetailInfo(@PathVariable("ds") String ds, @PathVariable("table") String table) {
        return Res.success(structService.tableDetailInfo(ds, table));
    }

    /**
     * 获取所有的表名称集合
     * @return
     */
    @GetMapping("{ds}/all")
    public Res<List<String>> tableNames(@PathVariable("ds") String ds) {
        return Res.success(structService.tableNames(ds));
    }

    /**
     * 表文档生成
     * @return
     */
    @GetMapping("dsTableDoc/{ds}")
    public void dsTableDoc(HttpServletResponse response, @PathVariable("ds") String ds) throws IOException {
        structService.dsTableDoc(response, ds);
    }

    /**
     * SQL库创建语句
     * @return
     */
    @GetMapping("dbCreateStatement/{ds}")
    public void dbCreateStatement(HttpServletResponse response, @PathVariable("ds") String ds) throws IOException {
        structService.dbCreateStatement(response, ds);
    }
}
