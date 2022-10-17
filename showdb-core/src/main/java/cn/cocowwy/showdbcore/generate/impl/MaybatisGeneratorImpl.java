package cn.cocowwy.showdbcore.generate.impl;

import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.entities.GenerateDefind;
import cn.cocowwy.showdbcore.util.CodeGenerateUtil;
import cn.cocowwy.showdbcore.generate.GeneratorService;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * MyBatis文件生成器
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
public class MaybatisGeneratorImpl implements GeneratorService {
    private Boolean checkIsMySQL(String ds) {
        return GlobalContext.getDataSourcesTypeMap().get(ds).equals(DBEnum.MySQL);
    }

    public void generate(String ds, String tableName, GenerateDefind generateDefind) {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        Configuration config = new Configuration();
        Context context = new Context(ModelType.CONDITIONAL);

        JDBCConnectionConfiguration jdbcConfig = new JDBCConnectionConfiguration();
        // 构造 JDBCConnectionConfiguration 连接信息
        buildJdbcConfig(jdbcConfig, GlobalContext.getDataSourcesMap().get(ds));

        context.addProperty("javaFileEncoding", "UTF-8");
        context.addProperty("autoDelimitKeywords", "true");
        // 由于beginningDelimiter和endingDelimiter的默认值为双引号(")，在Mysql中不能这么写，所以还要将这两个默认值改为`
        context.addProperty("beginningDelimiter", "`");
        context.addProperty("endingDelimiter", "`");
        context.addProperty("useActualColumnNames", "true");
        context.addProperty("useInformationSchema", "true");
        context.setJdbcConnectionConfiguration(jdbcConfig);
        config.addContext(context);

        TableConfiguration tableConfig = new TableConfiguration(context);
        tableConfig.setUpdateByExampleStatementEnabled(generateDefind.getUseExample());
        tableConfig.setCountByExampleStatementEnabled(generateDefind.getUseExample());
        tableConfig.setDeleteByExampleStatementEnabled(generateDefind.getUseExample());
        tableConfig.setSelectByExampleStatementEnabled(generateDefind.getUseExample());
        tableConfig.setTableName(tableName);
        tableConfig.setDomainObjectName(CodeGenerateUtil.className(tableName));
        tableConfig.setSchema(ds);
        if (!StringUtils.isEmpty(generateDefind.getMapperName())) {
            tableConfig.setMapperName(generateDefind.getMapperName());
        }

        // 设置生成路径
        JavaModelGeneratorConfiguration modelConfig = new JavaModelGeneratorConfiguration();
        SqlMapGeneratorConfiguration mapperConfig = new SqlMapGeneratorConfiguration();
        JavaClientGeneratorConfiguration daoConfig = new JavaClientGeneratorConfiguration();
        setGenerateFilePath(generateDefind, modelConfig, mapperConfig, daoConfig);

        context.setId("myid");
        context.addTableConfiguration(tableConfig);
        context.setJdbcConnectionConfiguration(jdbcConfig);
        context.setJavaModelGeneratorConfiguration(modelConfig);
        context.setSqlMapGeneratorConfiguration(mapperConfig);
        context.setJavaClientGeneratorConfiguration(daoConfig);
        // Comment
        CommentGeneratorConfiguration commentConfig = new CommentGeneratorConfiguration();
        commentConfig.addProperty("columnRemarks", "true");
        commentConfig.addProperty("annotations", "true");
        context.setCommentGeneratorConfiguration(commentConfig);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);

        //实体添加序列化
        PluginConfiguration serializablePluginConfiguration = new PluginConfiguration();
        serializablePluginConfiguration.addProperty("type", "org.mybatis.generator.plugins.SerializablePlugin");
        serializablePluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
        context.addPluginConfiguration(serializablePluginConfiguration);

        // toString, hashCode, equals插件
        PluginConfiguration equalsHashCodePlugin = new PluginConfiguration();
        equalsHashCodePlugin.addProperty("type", "org.mybatis.generator.plugins.EqualsHashCodePlugin");
        equalsHashCodePlugin.setConfigurationType("org.mybatis.generator.plugins.EqualsHashCodePlugin");
        context.addPluginConfiguration(equalsHashCodePlugin);
        PluginConfiguration toStringPlugin = new PluginConfiguration();
        toStringPlugin.addProperty("type", "org.mybatis.generator.plugins.ToStringPlugin");
        toStringPlugin.setConfigurationType("org.mybatis.generator.plugins.ToStringPlugin");
        context.addPluginConfiguration(toStringPlugin);

        if (generateDefind.getUseExample()) {
            if (checkIsMySQL(ds)) { // 校验是否是MySQl
                PluginConfiguration pluginConfiguration = new PluginConfiguration();
                pluginConfiguration.addProperty("useExample", "true");
                pluginConfiguration.addProperty("type", "cn.cocowwy.showdbcore.plugin.CommonDAOInterfacePlugin");
                pluginConfiguration.setConfigurationType("cn.cocowwy.showdbcore.plugin.CommonDAOInterfacePlugin");
                context.addPluginConfiguration(pluginConfiguration);
            }
        }

        context.setTargetRuntime("MyBatis3");
        try {
            // 创建
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException("创建MyBatis文件报错，e", e);
        }
    }

    /**
     * 设置生成路径
     * @param generateDefind 定义信息
     * @param modelConfig 实体类
     * @param mapperConfig mapper
     * @param daoConfig dao
     */
    void setGenerateFilePath(GenerateDefind generateDefind,
                             JavaModelGeneratorConfiguration modelConfig,
                             SqlMapGeneratorConfiguration mapperConfig,
                             JavaClientGeneratorConfiguration daoConfig) {
        // todo
//        modelConfig.setTargetPackage("cn.cocowwy.showdbtest.model");
        modelConfig.setTargetPackage(generateDefind.getModelPackagePath());
//        modelConfig.setTargetProject("/Users/cocowwy/Desktop/space/idea-space/ShowDB/showdb-test/src/main/java");
        modelConfig.setTargetProject(generateDefind.getModelProjectPath());
        //        mapperConfig.setTargetPackage("mybatis.mapper");
        mapperConfig.setTargetPackage(generateDefind.getMapperJavaPackagePath());
//        mapperConfig.setTargetProject("/Users/cocowwy/Desktop/space/idea-space/ShowDB/showdb-test/src/main/resources");
        mapperConfig.setTargetProject(generateDefind.getMapperJavaProjectPath());
        daoConfig.setConfigurationType("XMLMAPPER");
//        daoConfig.setTargetPackage("cn.cocowwy.showdbtest.mapper");
        daoConfig.setTargetPackage(generateDefind.getMapperJavaPackagePath());
//        daoConfig.setTargetProject("/Users/cocowwy/Desktop/space/idea-space/ShowDB/showdb-test/src/main/java");
        daoConfig.setTargetProject(generateDefind.getMapperJavaProjectPath());
    }

    /**
     * 通过反射获取DadaSource的账号密码信息
     *
     * @param jdbcConfig jdbc配置
     * @param dataSource datasource
     */
    public void buildJdbcConfig(JDBCConnectionConfiguration jdbcConfig, DataSource dataSource) {
        if (Objects.isNull(dataSource)) {
            throw new RuntimeException("非法数据数据源");
        }
        try {
            Class<?> c = dataSource.getClass();
            Method getPassword = c.getMethod("getPassword", null);
            Method getUsername = c.getMethod("getUsername", null);
            Method getDriverClassName = c.getMethod("getDriverClassName", null);

            String username = String.valueOf(getUsername.invoke(dataSource, null));
            String pwd = String.valueOf(getPassword.invoke(dataSource, null));
            String driverClassName = String.valueOf(getDriverClassName.invoke(dataSource, null));

            jdbcConfig.setConnectionURL(dataSource.getConnection().getMetaData().getURL());
            jdbcConfig.setUserId(username);
            jdbcConfig.setPassword(pwd);
            jdbcConfig.setDriverClass(driverClassName);
        } catch (Exception e) {
            throw new RuntimeException("获取数据源信息异常", e);
        }
    }
}
