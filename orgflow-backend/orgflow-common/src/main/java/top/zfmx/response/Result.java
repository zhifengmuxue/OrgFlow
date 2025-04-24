package top.zfmx.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应类
 *
 * @author lima
 * @version 0.0.1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Code code;
    private String message;
    private T data;

    /**
     * 成功响应，自定义消息，携带数据
     * @param message 消息
     * @param data 数据
     * @return 响应类
     * @param <T> 范型参数
     */
    public static<T> Result<T> ok(String message, T data) {
        return new Result<>(Code.SUCCESS,message, data);
    }

    /**
     * 成功响应，自定义消息
     * @param message 消息
     * @return 响应类
     * @param <T> 范型参数
     */
    public static<T> Result<T> ok(String message) {
        return ok(message,null);
    }

    /**
     * 成功响应，默认消息
     *
     * @return 响应类
     * @param <T> 无参数
     */
    public static<T> Result<T> ok(){
        return ok(Code.SUCCESS.getMessage());
    }

    /**
     * 失败响应，默认消息
     * @param code 500系统异常
     * @param message 消息
     * @return 响应类
     * @param <T> 范型参数
     */
    public static<T> Result<T> error(Code code, String message) {
        return new Result<>(Code.INTERNAL_ERROR,message, null);
    }
}
