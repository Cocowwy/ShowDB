package cn.cocowwy.showdbcore.strategy.impl.mysql;

import cn.cocowwy.showdbcore.config.ShowDbFactory;
import cn.cocowwy.showdbcore.strategy.MonitorExecuteStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Cocowwy
 * @create 2022-03-03-20:40
 */
@Component
public class MySqlMonitorExecuteStrategy implements MonitorExecuteStrategy {

    /**
     * 查询每个IP所占用的连接数
     * @return
     */
    @Override
    public Map<String, Integer> ipConnectCount() {
        String sql = "select SUBSTRING_INDEX(host, ':', 1) as ip, count(*) as count\n" +
                "from information_schema.processlist as a\n" +
                "group by ip\n" +
                "order by count desc;\n";
        ShowDbFactory.getJdbcTemplate()
                .queryForMap(sql);
        return null;
    }
}
