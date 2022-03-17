package cn.cocowwy.showdbui.service;

import cn.cocowwy.showdbcore.cache.ShowDbCache;
import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.constants.CacheKey;
import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.entities.TableStruct;
import cn.cocowwy.showdbcore.strategy.StructExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlExecuteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 数据源类型所对应的执行策略
     */
    private static final Map<DBEnum, StructExecuteStrategy> MONITOR_STRATEGY = new HashMap<>(1);

    @PostConstruct
    void init() {
        structExecuteStrategies.forEach(s -> {
            if (s instanceof MySqlExecuteStrategy) {
                MONITOR_STRATEGY.put(DBEnum.MySQL, s);
            }
        });
    }

    /**
     * 获取所有表的结构
     * Get the structure of all tables
     * @return
     */
    public List<List<TableStruct>> allTableStruct() {
        if (ShowDbCache.get(CacheKey.ALL_TABLE_STRUCT) != null) {
            return (List<List<TableStruct>>) ShowDbCache.get(CacheKey.ALL_TABLE_STRUCT);
        }

        List<String> tables = MONITOR_STRATEGY.get(GlobalContext.getDatabase()).tableNames();
        List<List<TableStruct>> rts = tables.stream()
                .map(t -> MONITOR_STRATEGY.get(GlobalContext.getDatabase()).tableStructure(t))
                .collect(Collectors.toList());

        ShowDbCache.put(CacheKey.ALL_TABLE_STRUCT, rts);

        return rts;
    }
}
