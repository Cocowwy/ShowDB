package cn.cocowwy.showdbcore.generate.impl;

import cn.cocowwy.showdbcore.entities.GenerateDefind;
import cn.cocowwy.showdbcore.generate.GeneratorService;
import org.springframework.stereotype.Service;

/**
 * 生成MyBatisPlus相关文件
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
public class MyBatisPlusGeneratorImpl implements GeneratorService {

    @Override
    public Boolean generate(String ds, String tableName, GenerateDefind generateDefind) {
        return false;
    }
}
