package cn.cocowwy.showdbcore.generate;

import cn.cocowwy.showdbcore.entities.GenerateDefind;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public interface GeneratorService {
    void generate(String ds, String tableName, GenerateDefind generateDefind) throws Exception;
}
