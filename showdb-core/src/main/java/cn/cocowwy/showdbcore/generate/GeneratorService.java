package cn.cocowwy.showdbcore.generate;

import cn.cocowwy.showdbcore.entities.GenerateDefind;

/**
 * 文件生成接口
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public interface GeneratorService {
    Boolean generate(String ds, String tableName, GenerateDefind generateDefind);
}
