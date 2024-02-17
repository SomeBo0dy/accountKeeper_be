package pers.xyj.modules.common.handler.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.market.domain.ResponseResult;

@RestControllerAdvice//Controller爆出异常会在这里处理，并添加到响应体
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        log.error("出现了异常{}",e);
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }
    @ExceptionHandler(AccessDeniedException.class)
    public void exceptionHandler(AccessDeniedException e){
        throw e;
    }
    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e){
        log.error("出现了异常{}",e);
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,e.getMessage());
    }
}
