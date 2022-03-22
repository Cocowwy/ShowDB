package cn.cocowwy.showdbui.service;

import cn.cocowwy.showdbcore.cache.ShowDbCache;
import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.entities.TableStruct;
import cn.cocowwy.showdbcore.strategy.StructExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlExecuteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
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
    public List<List<TableStruct>> allTableStruct() {
        String key = ShowDbCache.buildCacheKey(
                GlobalContext.getDatabaseProductName(),
                "tableStruct", "all");

        List<List<TableStruct>> rs = (List<List<TableStruct>>) ShowDbCache.get(key);

        if (rs == null) {
            List<String> tables = STRUCT_STRATEGY.get(GlobalContext.getDatabase()).tableNames();
            rs = tables.stream()
                    .map(t -> STRUCT_STRATEGY.get(GlobalContext.getDatabase()).tableStructure(t))
                    .collect(Collectors.toList());

            ShowDbCache.put(key, rs);
        }
        return rs;
    }

    /**
     * 表创建语句
     * Table creation statement
     * @param table 表名
     * @return
     */
    public String tableCreateStatement(String table) {
        String key = ShowDbCache.buildCacheKey(
                GlobalContext.getDatabaseProductName(),
                "createStatement",
                table);
        String rs = (String) ShowDbCache.get(key);

        if (rs == null) {
            rs = STRUCT_STRATEGY.get(GlobalContext.getDatabase()).createTableStatement(table);
            if (!StringUtils.isEmpty(rs)) {
                ShowDbCache.put(key, rs);
            }
        }
        return rs;
    }
}
