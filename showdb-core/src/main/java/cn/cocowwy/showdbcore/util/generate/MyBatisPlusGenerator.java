package cn.cocowwy.showdbcore.util.generate;

import cn.cocowwy.showdbcore.entities.GenerateCodeBlock;
import cn.cocowwy.showdbcore.entities.GenerateDefind;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 生成MyBatisPlus相关文件
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
public class MyBatisPlusGenerator implements FileGenerator {
    @Override
    public List<GenerateCodeBlock> generate(String ds, String tableName, GenerateDefind defind) {
        return null;
    }
}
