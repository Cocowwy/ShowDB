package cn.cocowwy.showdbui.service;

import cn.cocowwy.showdbcore.config.GlobalContext;
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
     * 兼容切换数据源后（包含不同数据库之间的数据源切换）
     */
    private static final Map<DBEnum, StructExecuteStrategy> MONITOR_STRATEGY = new HashMap(1);

    @PostConstruct
    void init() {
        structExecuteStrategies.forEach(s -> {
            if (s instanceof MySqlExecuteStrategy) {
                MONITOR_STRATEGY.put(DBEnum.MySQL, s);
            }
        });
    }

    public List<TableStruct> allTableStruct() {
        List<String> tables = MONITOR_STRATEGY.get(GlobalContext.getDatabase()).tableNames();
        List<TableStruct> rts = tables.stream()
                .map(t -> MONITOR_STRATEGY.get(GlobalContext.getDatabase()).tableStructure(t))
                .collect(Collectors.toList());
        return rts;
    }
}
