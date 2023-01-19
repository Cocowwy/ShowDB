package cn.cocowwy.showdbcore.entities;

/**
 * MyBatis 代码生成自定义生成器
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class MyBatisGenerateDefinition extends GenerateFileDefinition {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 是否使用lombok
     */
    private Boolean useLombok = Boolean.FALSE;
    /**
     * 是否生成Doc
     */
    private Boolean generateDoc = Boolean.TRUE;
    /**
     * Mapper的名字
     */
    private String mapperName;
    /**
     * MyBatis是否生成Example文件
     */
    private Boolean useExample = Boolean.FALSE;
    /**
     * model生成的项目路径
     */
    private String modelProjectPath;
    /**
     * model生成的包路径
     */
    private String modelPackagePath;
    /**
     * mapper的XML文件生成的项目路径
     */
    private String mapperXmlProjectPath;
    /**
     * mapper的XML文件生成的包路径
     */
    private String mapperXmlPackagePath = "mybatis.mapper";
    /**
     * mapper的java文件生成的项目路径
     */
    private String mapperJavaProjectPath;
    /**
     * mapper的java文件生成的包路径
     */
    private String mapperJavaPackagePath;
    /**
     * 使用toString插件
     */
    private Boolean useToString = Boolean.TRUE;
    /**
     * 使用Eq和Hx插件
     */
    private Boolean useEqAndHx = Boolean.TRUE;
    /**
     * JSR310日期库
     */
    private Boolean useJSR310 = Boolean.TRUE;
    /**
     * 作者
     */
    private String author = "https://github.com/Cocowwy/ShowDB";
    /**
     * 是否生成批量方法
     */
    private Boolean useBatch = Boolean.TRUE;
    /**
     * domain名
     */
    private String domainObjName;
    /**
     * 是否覆盖XML文件
     */
    private Boolean overrideXML = Boolean.TRUE;

    public Boolean getUseLombok() {
        return useLombok;
    }

    public void setUseLombok(Boolean useLombok) {
        this.useLombok = useLombok;
    }

    public Boolean getGenerateDoc() {
        return generateDoc;
    }

    public void setGenerateDoc(Boolean generateDoc) {
        this.generateDoc = generateDoc;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public Boolean getUseExample() {
        return useExample;
    }

    public void setUseExample(Boolean useExample) {
        this.useExample = useExample;
    }

    public String getModelProjectPath() {
        return modelProjectPath;
    }

    public void setModelProjectPath(String modelProjectPath) {
        this.modelProjectPath = modelProjectPath;
    }

    public String getModelPackagePath() {
        return modelPackagePath;
    }

    public void setModelPackagePath(String modelPackagePath) {
        this.modelPackagePath = modelPackagePath;
    }

    public String getMapperXmlProjectPath() {
        return mapperXmlProjectPath;
    }

    public void setMapperXmlProjectPath(String mapperXmlProjectPath) {
        this.mapperXmlProjectPath = mapperXmlProjectPath;
    }

    public String getMapperXmlPackagePath() {
        return mapperXmlPackagePath;
    }

    public void setMapperXmlPackagePath(String mapperXmlPackagePath) {
        this.mapperXmlPackagePath = mapperXmlPackagePath;
    }

    public String getMapperJavaProjectPath() {
        return mapperJavaProjectPath;
    }

    public void setMapperJavaProjectPath(String mapperJavaProjectPath) {
        this.mapperJavaProjectPath = mapperJavaProjectPath;
    }

    public String getMapperJavaPackagePath() {
        return mapperJavaPackagePath;
    }

    public void setMapperJavaPackagePath(String mapperJavaPackagePath) {
        this.mapperJavaPackagePath = mapperJavaPackagePath;
    }

    public Boolean getUseToString() {
        return useToString;
    }

    public void setUseToString(Boolean useToString) {
        this.useToString = useToString;
    }

    public Boolean getUseEqAndHx() {
        return useEqAndHx;
    }

    public void setUseEqAndHx(Boolean useEqAndHx) {
        this.useEqAndHx = useEqAndHx;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getUseJSR310() {
        return useJSR310;
    }

    public void setUseJSR310(Boolean useJSR310) {
        this.useJSR310 = useJSR310;
    }

    public Boolean getUseBatch() {
        return useBatch;
    }

    public void setUseBatch(Boolean useBatch) {
        this.useBatch = useBatch;
    }

    public String getDomainObjName() {
        return domainObjName;
    }

    public void setDomainObjName(String domainObjName) {
        this.domainObjName = domainObjName;
    }

    public Boolean getOverrideXML() {
        return overrideXML;
    }

    public void setOverrideXML(Boolean overrideXML) {
        this.overrideXML = overrideXML;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
