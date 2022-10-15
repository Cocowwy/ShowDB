package cn.cocowwy.showdbcore.entities;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * 代码生成自定义生成器
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class GenerateDefind {
    private Boolean useLombok = Boolean.FALSE;

    private Boolean generateDoc = Boolean.TRUE;

    private String mapperName;

    private Boolean useExample = Boolean.FALSE;

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
}
