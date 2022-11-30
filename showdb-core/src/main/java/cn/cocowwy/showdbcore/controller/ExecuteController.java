package cn.cocowwy.showdbcore.controller;

import cn.cocowwy.showdbcore.entities.ExecuteVo;
import cn.cocowwy.showdbcore.entities.Res;
import cn.cocowwy.showdbcore.service.ExecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

/**
 * SQL执行器
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@RestController
@RequestMapping("/showdb/execute")
public class ExecuteController {
    @Autowired
    private ExecuteService executeService;

    @PostMapping("/{ds}/{sql}/{limit}")
    public Res<ExecuteVo> execute(@PathVariable("ds") String ds, @PathVariable("sql") String sql, @PathVariable("limit") Integer limit) {
        try {
            List<Map<String, Object>> data = executeService.executeSql(sql, ds, limit);
            ExecuteVo vo = new ExecuteVo();
            vo.setColum(data.get(0).keySet());
            vo.setData(data);
            return Res.success(vo);
        } catch (Exception e) {
            return Res.error(e.getMessage());
        }
    }
}
