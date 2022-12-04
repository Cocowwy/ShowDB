package cn.cocowwy.showdbcore.strategy.impl.mysql;

import cn.cocowwy.showdbcore.config.ShowDbFactory;
import cn.cocowwy.showdbcore.service.ExecuteService;
import cn.cocowwy.showdbcore.strategy.MySqlExecuteStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
public class MySqlExecuteServiceImpl extends MySqlExecuteStrategy implements ExecuteService {
    @Override
    public List<Map<String, Object>> executeSql(String sql, String ds, Integer limit) {
        if (sql.contains("limit")) {
            return ShowDbFactory.getJdbcTemplate(ds).queryForList(sql);
        } else {
            return ShowDbFactory.getJdbcTemplate(ds).queryForList(sql + " limit " + limit);
        }
    }
}
