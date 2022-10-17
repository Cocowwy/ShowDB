package cn.cocowwy.showdbcore.entities;

/**
 * 代码生成自定义生成器
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class GenerateDefind {
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
}
