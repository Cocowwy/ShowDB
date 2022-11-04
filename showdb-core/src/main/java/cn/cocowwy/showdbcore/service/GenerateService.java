package cn.cocowwy.showdbcore.service;

import cn.cocowwy.showdbcore.config.ShowDbFactory;
import cn.cocowwy.showdbcore.entities.GenerateDefind;
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

    public GenerateDefind defindConfig(String ds, String tableName) {
        String classPath;
        try {
            classPath = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            throw new ShowDbException("Failed to get classpath,", e);
        }

        String CLASS_PATH_SUFFIX = "/target/classes/";
        if (!classPath.endsWith(CLASS_PATH_SUFFIX)) {
            throw new ShowDbException("Unresolved classpath: " + classPath);
        }

        String JAVA_SRC_PATH = "/src/main/java";
        String defaultProjectSrcPath = classPath.replace(CLASS_PATH_SUFFIX, "") + JAVA_SRC_PATH;
        String JAVA_RESOURCES_PATH = "/src/main/resources";
        String defaultProjectResPath = classPath.replace(CLASS_PATH_SUFFIX, "") + JAVA_RESOURCES_PATH;
        String defaultPkgName = ShowDbFactory.getApplicationContext().getBeansWithAnnotation(SpringBootApplication.class)
                .values().toArray()[0].getClass().getPackage().getName();

        // 设置默认的文件创建路径
        GenerateDefind defind = new GenerateDefind();
        defind.setModelPackagePath(defaultPkgName + ".model");
        defind.setModelProjectPath(defaultProjectSrcPath);
        defind.setMapperJavaPackagePath(defaultPkgName + ".mapper");
        defind.setMapperJavaProjectPath(defaultProjectSrcPath);
        defind.setMapperXmlProjectPath(defaultProjectResPath);
        defind.setDomainObjName(CodeGenerateUtil.className(tableName));
        defind.setMapperName(CodeGenerateUtil.className(tableName) + "Mapper");
        defind.setTableName(tableName);

        return defind;
    }

    public Boolean mybatis(GenerateDefind generateDefind, String ds) {
        return mybatisGeneratorImpl.generate(ds, generateDefind.getTableName(), generateDefind);
    }
}
