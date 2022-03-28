package cn.cocowwy.showdbui.service;

import cn.cocowwy.showdbcore.cache.ShowDbCache;
import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.entities.TableField;
import cn.cocowwy.showdbcore.entities.TableInfo;
import cn.cocowwy.showdbcore.entities.TableStructVo;
import cn.cocowwy.showdbcore.strategy.StructExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlExecuteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-11:15
 */
@Service
public class StructService {
    @Autowired
    List<StructExecuteStrategy> structExecuteStrategies;
    /**
     * 数据源类型所对应的执行策略，适配切换数据源能路由到指定策略
     */
    private static final Map<DBEnum, StructExecuteStrategy> STRUCT_STRATEGY = new HashMap<>(1);

    @PostConstruct
    void init() {
        structExecuteStrategies.forEach(s -> {
            if (s instanceof MySqlExecuteStrategy) {
                STRUCT_STRATEGY.put(DBEnum.MySQL, s);
            }
        });
    }

    /**
     * 获取所有表的结构
     * Get the structure of all tables
     * @return
     */
    public TableStructVo tableStruct(Integer pageSize, Integer pageNumber) {
        String key = ShowDbCache.buildCacheKey(
                GlobalContext.getCurrentDataSourceBeanName(),
                "tableStruct", pageSize + "@" + pageNumber);

        List<TableStructVo.TableStruct> rts = (List<TableStructVo.TableStruct>) ShowDbCache.cache().computeIfAbsent(key, (k) -> {
            List<String> tables = page(tableNames(), pageSize, pageNumber);
            List<TableStructVo.TableStruct> rt = tables.stream().map(table -> {
                List<TableField> tableFields = STRUCT_STRATEGY.get(GlobalContext.getDatabase()).tableStructure(table);
                TableInfo tableInfo = new TableInfo();
                tableInfo.setTableName(table);
                tableInfo.setTableComment(STRUCT_STRATEGY.get(GlobalContext.getDatabase()).tableInfo(table).getTableComment());
                TableStructVo.TableStruct tableStruct = new TableStructVo.TableStruct();
                tableStruct.setTableInfo(tableInfo);
                tableStruct.setTableFieldList(tableFields);
                return tableStruct;
            }).collect(Collectors.toList());
            return rt;
        });

        TableStructVo tableStructVo = new TableStructVo();
        tableStructVo.setTotal(tableNames().size());
        tableStructVo.setTableStructs(rts);

        return tableStructVo;
    }

    /**
     * 获取所有的表名称集合
     * Get a collection of all table names
     * @return
     */
    public List<String> tableNames() {
        String tablesKey = ShowDbCache.buildCacheKey(
                GlobalContext.getCurrentDataSourceBeanName(),
                "tableLists", "names");

        List<String> rts = (List<String>) ShowDbCache.cache().computeIfAbsent(tablesKey,
                (key) -> STRUCT_STRATEGY.get(GlobalContext.getDatabase()).tableNames());
        return rts;
    }

    /**
     * 获取表结构
     * Get the structure of all tables
     * @return
     */
    public TableStructVo tableStruct(Integer pageSize, Integer pageNumber, String name) {

        String key = ShowDbCache.buildCacheKey(
                GlobalContext.getCurrentDataSourceBeanName(),
                "tableStruct", name + "#" + pageSize + "#" + pageNumber);

        List<String> tabkes = tableNames().stream().filter(it -> it.contains(name)).collect(Collectors.toList());
        List<TableStructVo.TableStruct> rts = (List<TableStructVo.TableStruct>) ShowDbCache.cache().computeIfAbsent(key, (k) -> {
            List<String> likeTale = page(tabkes, pageSize, pageNumber);
            List<TableStructVo.TableStruct> rt = likeTale.stream().map(table -> {
                List<TableField> tableFields = STRUCT_STRATEGY.get(GlobalContext.getDatabase()).tableStructure(table);
                TableInfo tableInfo = new TableInfo();
                tableInfo.setTableName(table);
                tableInfo.setTableComment(STRUCT_STRATEGY.get(GlobalContext.getDatabase()).tableInfo(table).getTableComment());
                TableStructVo.TableStruct tableStruct = new TableStructVo.TableStruct();
                tableStruct.setTableInfo(tableInfo);
                tableStruct.setTableFieldList(tableFields);
                return tableStruct;
            }).collect(Collectors.toList());
            return rt;
        });

        TableStructVo tableStructVo = new TableStructVo();
        tableStructVo.setTotal(tabkes.size());
        tableStructVo.setTableStructs(rts);
        return tableStructVo;
    }

    /**
     * 表创建语句
     * Table creation statement
     * @param table 表名
     * @return
     */
    public String tableCreateStatement(String table) {
        String key = ShowDbCache.buildCacheKey(
                GlobalContext.getCurrentDataSourceBeanName(),
                "createStatement",
                table);
        return (String) ShowDbCache.cache()
                .computeIfAbsent(key, (k) -> STRUCT_STRATEGY.get(GlobalContext.getDatabase()).createTableStatement(table));

    }

    private static <T> List<T> page(List<T> data, int size, int num) {
        if (data.size() < 1) {
            return new ArrayList<>(0);
        }
        int totalNum = data.size();
        // start 1
        int pageNum = num;
        int pageSize = size;
        int totalPage = 0;
        if (totalNum > 0) {
            totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        }
        if (pageNum > totalPage) {
            pageNum = totalPage;
        }
        int startPoint = (pageNum - 1) * pageSize;
        int endPoint = startPoint + pageSize;
        if (totalNum <= endPoint) {
            endPoint = totalNum;
        }
        return data.subList(startPoint, endPoint);
    }
}
