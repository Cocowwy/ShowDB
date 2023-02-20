package cn.cocowwy.showdbcore.entities;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class ExecuteVo {
    private Set<String> colum ;
    private List<Map<String, Object>> data;

    public Set<String> getColum() {
        return colum;
    }

    public void setColum(Set<String> colum) {
        this.colum = colum;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }
}
