package cn.cocowwy.showdbcore.entities;

import java.io.Serializable;

/**
 * @author Cocowwy
 * @create 2022-03-03-20:47
 */
public class Result<T> implements Serializable {
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

    public Result() {
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return success("操作成功", data);
    }

    public static <T> Result<T> successMsg(String msg) {
        return success(msg, null);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(Result.SUCCESS, msg, data);
    }

    public static <T> Result<T> error(String msg) {
        return error(Result.ERROR, msg);
    }

    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message, null);
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
