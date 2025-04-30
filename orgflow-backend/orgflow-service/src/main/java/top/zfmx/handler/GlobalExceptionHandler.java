package top.zfmx.handler;

import cn.dev33.satoken.exception.NotLoginException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.zfmx.response.Code;
import top.zfmx.response.Result;

/**
 * 全局异常捕获
 *
 * @author lima
 * @version 0.0.1
 **/
@Slf4j
@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e){
        log.error("系统异常：", e);
        return Result.error(Code.INTERNAL_ERROR, "系统异常");
    }

    @ExceptionHandler(NotLoginException.class)
    public Result<String> notLoginException(NotLoginException e){
        log.error(e.getMessage());
        return Result.error(Code.UNAUTHORIZED, "用户未登录");
    }
}
