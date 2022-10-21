package cn.cocowwy.showdb.configuration;

/**
 * ShowDB插件功能
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class Plugin {
    /**
     * 代码生成功能插件
     */
    private Boolean generate = Boolean.TRUE;

    public Boolean getGenerate() {
        return generate;
    }

    public void setGenerate(Boolean generate) {
        this.generate = generate;
    }
}
