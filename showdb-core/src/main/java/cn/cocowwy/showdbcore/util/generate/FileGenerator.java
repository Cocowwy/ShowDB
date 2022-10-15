package cn.cocowwy.showdbcore.util.generate;

import cn.cocowwy.showdbcore.entities.GenerateCodeBlock;
import cn.cocowwy.showdbcore.entities.GenerateDefind;

import java.util.List;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public interface FileGenerator {
    List<GenerateCodeBlock> generate(String ds, String tableName, GenerateDefind defind);
}
