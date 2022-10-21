package cn.cocowwy.showdbcore.entities;

import cn.cocowwy.showdbcore.exception.Error;

import java.io.Serializable;

/**
 * 统一封装ShowDB的返回
 * @author Cocowwy
 * @create 2022-03-03-20:47
 */
public class Res<T> implements Serializable {
    private static final long serialVersionUID = -1491499610244551029L;

    public static final int SUCCESS = 200;
    public static final int ERROR = 500;

    /**
     * 状态码 1: success, 0: error
     */
    private int code;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 结果数据
     */
    private T data;

    public Res() {
    }

    public Res(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Res<T> success() {
        return success(null);
    }

    public static <T> Res<T> success(T data) {
        return success("操作成功", data);
    }

    public static <T> Res<T> successMsg(String msg) {
        return success(msg, null);
    }

    public static <T> Res<T> success(String msg, T data) {
        return new Res<>(Res.SUCCESS, msg, data);
    }

    public static <T> Res<T> error(String msg) {
        return error(Res.ERROR, msg);
    }

    public static <T> Res<T> error(Error error) {
        return new Res(error.getCode(), error.getMessage(), null);
    }

    public static <T> Res<T> error(int code, String message) {
        return new Res<>(code, message, null);
    }

    public boolean isOk() {
        return code == SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
