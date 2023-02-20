package cn.cocowwy.showdbcore.service;

import cn.cocowwy.showdbcore.config.ShowDbCache;
import cn.cocowwy.showdbcore.config.ShowDBContext;
import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.entities.DsInfo;
import cn.cocowwy.showdbcore.entities.IpCount;
import cn.cocowwy.showdbcore.entities.SlaveStatus;
import cn.cocowwy.showdbcore.entities.TranscationalStatus;
import cn.cocowwy.showdbcore.strategy.MonitorExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.MySqlExecuteStrategy;
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
     * 数据源类型所对应的执行策略，适配切换数据源能路由到指定策略
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

    /**
     * 当前数据源所对应数据库的 IP客户端连接数
     * @return
     */
    public List<IpCount> ipCountInfo(String ds) {
        return MONITOR_STRATEGY.get(ShowDBContext.mapDs2DbType(ds)).ipConnectCount(ds);
    }

    /**
     * 数据库主从连接信息，不走缓存，仅缓存未开启数据源的ds，降低db命中
     *  -MySQL 仅开启主从之后有返回值
     * @return
     */
    public SlaveStatus masterSlaveInfo(String ds) {
        String key = ShowDbCache.buildCacheKey(ds, "un-use-master-slave", ds);
        // 不走缓存，仅仅标记当前数据源是否开启主从复制
        if (!ShowDbCache.cache().containsKey(key)) {
            SlaveStatus slaveStatus = MONITOR_STRATEGY.get(ShowDBContext.mapDs2DbType(ds)).slaveStatus(ds);
            if (slaveStatus == null) {
                // 未开启主从同步，标记即可
                ShowDbCache.put(key, "un-use");
            }
            return slaveStatus;
        }
        return null;
    }

    /**
     * 数据库大小
     * @return
     */
    public DsInfo dsInfo(String ds) {
        return (DsInfo) ShowDbCache.cache()
                .computeIfAbsent(ShowDbCache.buildCacheKey(ds, "dsInfo", ds),
                        (key) -> MONITOR_STRATEGY.get(ShowDBContext.mapDs2DbType(ds)).dsInfo(ds));
    }

    /**
     * 事务监控
     * 实时，不走缓存
     * @param ds
     * @return
     */
    public List<TranscationalStatus> transcationalStatus(String ds) {
        return MONITOR_STRATEGY.get(ShowDBContext.mapDs2DbType(ds)).transcationalStatus(ds);
    }

    // select * from information_schema.innodb_trx where TIME_TO_SEC(timediff(now(),trx_started))>0 长事务查询
}
