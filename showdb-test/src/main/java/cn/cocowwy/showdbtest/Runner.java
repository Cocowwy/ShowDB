package cn.cocowwy.showdbtest;

import cn.cocowwy.showdbcore.entities.GenerateDefind;
import cn.cocowwy.showdbcore.generate.impl.MaybatisGeneratorImpl;
import cn.cocowwy.showdbcore.generate.impl.MyBatisPlusGeneratorImpl;
import cn.cocowwy.showdbcore.strategy.SqlExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlMonitorExecuteStrategy;
import cn.cocowwy.showdbcore.strategy.impl.mysql.MySqlStructExecuteStrategy;
import org.mybatis.generator.api.MyBatisGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-10:35
 */
@Component
public class Runner implements ApplicationRunner {
    @Value("${server.servlet.context-path}")
    private String context;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private List<DataSource> dataSource;
    @Autowired
    private List<SqlExecuteStrategy> strategy;
    @Autowired
    private MySqlStructExecuteStrategy mySqlStructExecuteStrategy;
    @Autowired
    private MySqlMonitorExecuteStrategy mySqlMonitorExecuteStrategy;
    @Autowired
    private List<MySqlExecuteStrategy> mySqlExecuteStrategies;
    @Autowired
    private MaybatisGeneratorImpl generator;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        testGenerateMyBatisFile();
    }

    /**
     * 测试创建MyBatis文件
     */
    public void testGenerateMyBatisFile() {
        GenerateDefind generateDefind = new GenerateDefind();
        generateDefind.setUseExample(true);
        // 实体类生成项目地址
        // 实体类生成包名
        generateDefind.setModelPackagePath("cn.cocowwy.showdbtest.model");
        generateDefind.setModelProjectPath("/Users/cocowwy/Desktop/space/idea-space/ShowDB/showdb-test/src/main/java");
        // mapper java文件生成项目地址
        // mapper java文件生成包地址
        generateDefind.setMapperJavaPackagePath("cn.cocowwy.showdbtest.mapper");
        generateDefind.setMapperJavaProjectPath("/Users/cocowwy/Desktop/space/idea-space/ShowDB/showdb-test/src/main/java");
        // mapper xml文件生成项目地址
        // mapper xml文件包地址
        generateDefind.setMapperXmlPackagePath("mybatis.mapper");
        generateDefind.setMapperXmlProjectPath("/Users/cocowwy/Desktop/space/idea-space/ShowDB/showdb-test/src/main/resources");
        new MaybatisGeneratorImpl().generate("cms", "cms_member_report", generateDefind);
    }
}
