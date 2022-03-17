package cn.cocowwy.showdbui.service;

import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.entities.IpCount;
import cn.cocowwy.showdbcore.strategy.MonitorExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlExecuteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cocowwy
 * @create 2022-03-03-22:08
 */
@Service
public class MonitorService {
    @Autowired
    List<MonitorExecuteStrategy> monitorExecuteStrategy;
    /**
     * 兼容切换数据源后（包含不同数据库之间的数据源切换），能切换监控策略
     */
    private static final Map<DBEnum, MonitorExecuteStrategy> MONITOR_STRATEGY = new HashMap(1);

    @PostConstruct
    void init() {
        monitorExecuteStrategy.forEach(s -> {
            if (s instanceof MySqlExecuteStrategy) {
                MONITOR_STRATEGY.put(DBEnum.MySQL, s);
            }
        });
    }

    public List<IpCount> ipCountInfo() {
        return MONITOR_STRATEGY.get(GlobalContext.getDatabase()).ipConnectCount();
    }
}
