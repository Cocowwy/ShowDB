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
    /**
     * SQL执行器
     */
    private Boolean execute = Boolean.TRUE;
    /**
     * 监控
     */
    private Boolean monitor = Boolean.TRUE;

    public Boolean getGenerate() {
        return generate;
    }

    public void setGenerate(Boolean generate) {
        this.generate = generate;
    }

    public Boolean getExecute() {
        return execute;
    }

    public void setExecute(Boolean execute) {
        this.execute = execute;
    }

    public Boolean getMonitor() {
        return monitor;
    }

    public void setMonitor(Boolean monitor) {
        this.monitor = monitor;
    }
}
