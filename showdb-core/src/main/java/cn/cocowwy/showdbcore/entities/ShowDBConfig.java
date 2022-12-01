package cn.cocowwy.showdbcore.entities;


import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * 配置信息 用于ShowDB前端读取配置
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class ShowDBConfig {
    private Customize customize;
    private Plugin plugin;

    public Customize getCustomize() {
        return customize;
    }

    public void setCustomize(Customize customize) {
        this.customize = customize;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    public ShowDBConfig(Customize customize, Plugin plugin) {
        this.customize = customize;
        this.plugin = plugin;
    }

    public static class Customize {
        /**
         * 首页alert文案
         */
        private String topAlert;
        /**
         * 创建者
         */
        private String creator;
        /**
         * 邮箱
         */
        private String email;
        /**
         * 描述
         */
        private String desc;
        /**
         * icon 图标地址
         */
        private String img;

        public String getTopAlert() {
            return topAlert;
        }

        public void setTopAlert(String topAlert) {
            this.topAlert = topAlert;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class Plugin {
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
}
