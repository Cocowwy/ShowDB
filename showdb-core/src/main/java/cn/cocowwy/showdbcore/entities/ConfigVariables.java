package cn.cocowwy.showdbcore.entities;

/**
 * DB配置参数
 *
 * @author Cocowwy
 * @create 2022-04-04-21:36
 */
public class ConfigVariables {
    /**
     * 系统版本
     */
    private String versionCompileOs;

    /**
     * 版本
     */
    private String version;

    /**
     * 安装路径
     */
    private String basedir;

    public String getVersionCompileOs() {
        return versionCompileOs;
    }

    public void setVersionCompileOs(String versionCompileOs) {
        this.versionCompileOs = versionCompileOs;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBasedir() {
        return basedir;
    }

    public void setBasedir(String basedir) {
        this.basedir = basedir;
    }
}
