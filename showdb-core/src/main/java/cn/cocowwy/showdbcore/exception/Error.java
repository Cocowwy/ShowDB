package cn.cocowwy.showdbcore.exception;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-10:39
 */
public class Error {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
