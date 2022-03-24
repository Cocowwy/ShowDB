package cn.cocowwy.showdbcore.exception;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-10:40
 */
public class ErrorDefinition {
    public static final Error UNUSE_ENDPOINT = new Error(100, "该功能未启用");
    public static final Error NOT_SUPPORTED = new Error(101, "暂不支持当前功能~");
    public static final Error SWITCH_DATA_SOURCE_ERROR = new Error(102, "数据源切换失败");
}
