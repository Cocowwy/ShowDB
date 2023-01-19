package cn.cocowwy.showdbcore.service;

import cn.cocowwy.showdbcore.config.ShowDBContext;
import cn.cocowwy.showdbcore.entities.MyBatisGenerateDefinition;
import cn.cocowwy.showdbcore.exception.ShowDbException;
import cn.cocowwy.showdbcore.generate.GeneratorService;
import cn.cocowwy.showdbcore.util.CodeGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
public class GenerateService {
    @Autowired
    private GeneratorService mybatisGeneratorImpl;

    public MyBatisGenerateDefinition defineConfig(String ds, String tableName) {
        String classPath;
        try {
            classPath = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            throw new ShowDbException("Failed to get classpath,", e);
        }

        String classPathSuffix = "/target/classes/";
        if (!classPath.endsWith(classPathSuffix)) {
            throw new ShowDbException("Unresolved classpath: " + classPath);
        }

        String javaSrcPath = "/src/main/java";
        String defaultProjectSrcPath = classPath.replace(classPathSuffix, "") + javaSrcPath;
        String javaResourcesPath = "/src/main/resources";
        String defaultProjectResPath = classPath.replace(classPathSuffix, "") + javaResourcesPath;
        String defaultPkgName = ShowDBContext.getApplicationContext().getBeansWithAnnotation(SpringBootApplication.class)
                .values().toArray()[0].getClass().getPackage().getName();

        // 设置默认的文件创建路径
        MyBatisGenerateDefinition defined = new MyBatisGenerateDefinition();
        defined.setModelPackagePath(defaultPkgName + ".model");
        defined.setModelProjectPath(defaultProjectSrcPath);
        defined.setMapperJavaPackagePath(defaultPkgName + ".mapper");
        defined.setMapperJavaProjectPath(defaultProjectSrcPath);
        defined.setMapperXmlProjectPath(defaultProjectResPath);
        defined.setDomainObjName(CodeGenerateUtil.className(tableName));
        defined.setMapperName(CodeGenerateUtil.className(tableName) + "Mapper");
        defined.setTableName(tableName);

        return defined;
    }

    public Boolean mybatis(MyBatisGenerateDefinition generateDefind, String ds) {
        return mybatisGeneratorImpl.generate(ds, generateDefind.getTableName(), generateDefind);
    }
}
