package cn.cocowwy.showdbcore.generate.impl;

import cn.cocowwy.showdbcore.config.ShowDBContext;
import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.entities.MyBatisGenerateDefinition;
import cn.cocowwy.showdbcore.entities.GenerateFileDefinition;
import cn.cocowwy.showdbcore.exception.ShowDbException;
import cn.cocowwy.showdbcore.generate.GeneratorService;
import cn.cocowwy.showdbcore.generate.custom.CustomCommentGenerator;
import cn.cocowwy.showdbcore.generate.plugin.BatchInsertPlugin;
import cn.cocowwy.showdbcore.generate.plugin.BatchUpdatePlugin;
import cn.cocowwy.showdbcore.generate.plugin.DaoInterfacePlugin;
import cn.cocowwy.showdbcore.generate.plugin.Jsr310ResolverPlugin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.plugins.EqualsHashCodePlugin;
import org.mybatis.generator.plugins.SerializablePlugin;
import org.mybatis.generator.plugins.ToStringPlugin;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * MyBatis文件生成器
 * MyBatis的文件生成基于开源项目 mybatis/generator 进行定制
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
public class MybatisGeneratorImpl implements GeneratorService {
    private static final Log logger = LogFactory.getLog(MybatisGeneratorImpl.class);

    /**
     * @param ds 当前数据源
     * @return 校验是否是MySQl
     */
    private Boolean checkIsMySQL(String ds) {
        return ShowDBContext.getDataSourcesTypeMap().get(ds).equals(DBEnum.MySQL);
    }

    @Override
    public Boolean generate(String ds, String tableName, GenerateFileDefinition generate) {
        MyBatisGenerateDefinition myBatisGenerate = (MyBatisGenerateDefinition) generate;
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        Configuration config = new Configuration();
        Context context = new Context(ModelType.CONDITIONAL);
        buildConfiguration(ds, tableName, myBatisGenerate, config, context);
        buildComment(context, myBatisGenerate);
        buildPlugins(ds, myBatisGenerate, context);
        context.setTargetRuntime("MyBatis3");
        try {
            // overrideXML
            if (myBatisGenerate.getOverrideXML()) {
                String mappingXMLFilePath = getMappingXMLFilePath(myBatisGenerate);
                File mappingXMLFile = new File(mappingXMLFilePath);
                if (mappingXMLFile.exists()) {
                    mappingXMLFile.delete();
                }
            }

            // do generate
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(new ProgressCallback() {
                @Override
                public void done() {
                    logger.info("Generate table [" + ds + "." + tableName + "] MyBatis's file done,Please click 'Reload from Disk' to refrsh directory");
                }
            });
            return true;
        } catch (Exception e) {
            logger.error("Error in creating [" + ds + "." + tableName + "] MyBatis's file", e);
            return false;
        }
    }

    /**
     * 构建配置信息
     * @param ds 数据源
     * @param tableName 表名
     * @param generateDefind 自定义信息
     * @param config 配置
     * @param context context
     */
    private void buildConfiguration(String ds, String tableName, MyBatisGenerateDefinition generateDefind, Configuration config, Context context) {
        JDBCConnectionConfiguration jdbcConfig = new JDBCConnectionConfiguration();

        // 构造 JDBCConnectionConfiguration 连接信息
        buildJdbcConfig(jdbcConfig, ShowDBContext.getDataSourcesMap().get(ds));

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
        tableConfig.setDomainObjectName(generateDefind.getDomainObjName());
        tableConfig.setCatalog(ds);
        if (!StringUtils.isEmpty(generateDefind.getMapperName())) {
            tableConfig.setMapperName(generateDefind.getMapperName());
        }

        // 设置生成路径
        JavaModelGeneratorConfiguration modelConfig = new JavaModelGeneratorConfiguration();
        SqlMapGeneratorConfiguration mapperConfig = new SqlMapGeneratorConfiguration();
        JavaClientGeneratorConfiguration daoConfig = new JavaClientGeneratorConfiguration();
        buildGenerateFilePath(generateDefind, modelConfig, mapperConfig, daoConfig);

        context.setId(tableName);
        context.addTableConfiguration(tableConfig);
        context.setJdbcConnectionConfiguration(jdbcConfig);
        context.setJavaModelGeneratorConfiguration(modelConfig);
        context.setSqlMapGeneratorConfiguration(mapperConfig);
        context.setJavaClientGeneratorConfiguration(daoConfig);
    }

    /**
     * 设置评论信息
     * @param context context
     * @param generateDefind generateDefind
     */
    private static void buildComment(Context context, MyBatisGenerateDefinition generateDefind) {
        CommentGeneratorConfiguration commentConfig = new CommentGeneratorConfiguration();
        commentConfig.addProperty("author", generateDefind.getAuthor());
        commentConfig.setConfigurationType(CustomCommentGenerator.class.getName());
        context.setCommentGeneratorConfiguration(commentConfig);
    }

    /**
     * 构建插件信息
     * @param ds 数据源
     * @param generateDefind 自定义信息
     * @param context context
     */
    private void buildPlugins(String ds, MyBatisGenerateDefinition generateDefind, Context context) {
        // 实体添加序列化
        PluginConfiguration serializablePluginConfiguration = new PluginConfiguration();
        serializablePluginConfiguration.setConfigurationType(SerializablePlugin.class.getName());
        context.addPluginConfiguration(serializablePluginConfiguration);

        // 批量新增更新插件
        if (generateDefind.getUseBatch()) {
            PluginConfiguration batchUpdPlugin = new PluginConfiguration();
            batchUpdPlugin.setConfigurationType(BatchUpdatePlugin.class.getName());
            context.addPluginConfiguration(batchUpdPlugin);

            PluginConfiguration batchIstPlugin = new PluginConfiguration();
            batchIstPlugin.setConfigurationType(BatchInsertPlugin.class.getName());
            context.addPluginConfiguration(batchIstPlugin);
        }

        // toString, hashCode和equals插件
        if (generateDefind.getUseToString()) {
            PluginConfiguration toStringPlugin = new PluginConfiguration();
            toStringPlugin.setConfigurationType(ToStringPlugin.class.getName());
            context.addPluginConfiguration(toStringPlugin);
        }
        if (generateDefind.getUseEqAndHx()) {
            PluginConfiguration equalsHashCodePlugin = new PluginConfiguration();
            equalsHashCodePlugin.setConfigurationType(EqualsHashCodePlugin.class.getName());
            context.addPluginConfiguration(equalsHashCodePlugin);
        }

        // 使用 example
        if (generateDefind.getUseExample()) {
            if (checkIsMySQL(ds)) {
                PluginConfiguration pluginConfiguration = new PluginConfiguration();
                pluginConfiguration.addProperty("useExample", generateDefind.getUseExample().toString());
                pluginConfiguration.addProperty("author", generateDefind.getAuthor());
                pluginConfiguration.setConfigurationType(DaoInterfacePlugin.class.getName());
                context.addPluginConfiguration(pluginConfiguration);
            }
        }

        // JSR310
        if (generateDefind.getUseJSR310()) {
            JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
            javaTypeResolverConfiguration.setConfigurationType(Jsr310ResolverPlugin.class.getName());
            context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
        }
    }

    /**
     * 设置生成路径
     * @param generateDefind 定义信息
     * @param modelConfig 实体类
     * @param mapperConfig XML
     * @param daoConfig 持久层
     */
    void buildGenerateFilePath(MyBatisGenerateDefinition generateDefind,
                               JavaModelGeneratorConfiguration modelConfig,
                               SqlMapGeneratorConfiguration mapperConfig,
                               JavaClientGeneratorConfiguration daoConfig) {
        // 实体生成地址
        modelConfig.setTargetPackage(generateDefind.getModelPackagePath());
        modelConfig.setTargetProject(generateDefind.getModelProjectPath());
        // DAO持久层
        daoConfig.setConfigurationType("XMLMAPPER");
        daoConfig.setTargetPackage(generateDefind.getMapperJavaPackagePath());
        daoConfig.setTargetProject(generateDefind.getMapperJavaProjectPath());
        // XML文件 生成地址
        mapperConfig.setTargetPackage(generateDefind.getMapperXmlPackagePath());
        mapperConfig.setTargetProject(generateDefind.getMapperXmlProjectPath());
    }

    /**
     * 通过反射获取DadaSource的账号密码信息
     *
     * @param jdbcConfig jdbc配置
     * @param dataSource datasource
     */
    public void buildJdbcConfig(JDBCConnectionConfiguration jdbcConfig, DataSource dataSource) {
        if (Objects.isNull(dataSource)) {
            throw new ShowDbException("Illegal data source");
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
            throw new ShowDbException("Reflection to obtain DataSource information exception ", e);
        }
    }

    private String getMappingXMLFilePath(MyBatisGenerateDefinition defind) {
        StringBuilder sb = new StringBuilder();
        sb.append(defind.getMapperXmlProjectPath()).append("/")
                .append(defind.getMapperXmlPackagePath().replace(".", "/")).append("/");
        if (!StringUtils.isEmpty(defind.getMapperName())) {
            sb.append(defind.getMapperName()).append(".xml");
        } else {
            sb.append(defind.getDomainObjName()).append("Mapper.xml");
        }
        return sb.toString();
    }
}
