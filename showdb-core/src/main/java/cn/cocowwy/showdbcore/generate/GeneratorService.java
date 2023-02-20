package cn.cocowwy.showdbcore.generate;

import cn.cocowwy.showdbcore.entities.GenerateFileDefinition;

/**
 * Generate file interface
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public interface GeneratorService<T extends GenerateFileDefinition> {
    /**
     *
     * @param ds DataSource bean Name
     * @param tableName TableName
     *
     * @return
     */
    Boolean generate(String ds, String tableName, T generate);
}
