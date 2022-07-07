package cn.cocowwy.showdbcore.entities;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class Customize {
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
