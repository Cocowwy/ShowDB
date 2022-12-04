package cn.cocowwy.showdbcore.service;

import java.util.List;
import java.util.Map;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public interface ExecuteService {
    List<Map<String, Object>> executeSql(String sql, String ds, Integer limit);
}
