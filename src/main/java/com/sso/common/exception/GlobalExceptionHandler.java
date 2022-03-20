package com.sso.common.exception;

import com.sso.common.ResponseData;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 使用该注解表示开启了全局异常的捕获
 */
@Log4j2
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 全局捕获业务异常
     *
     * @param ex 业务异常
     * @return 相应异常
     */
    @ExceptionHandler(BizException.class)
    public ResponseData<Object> runtimeExceptionHandler(BizException ex) {
        // TODO 后续根据日志级别打印日志
        String msg = null != (ex.getMessage()) ? ex.getMessage() : ResponseCodeEnum.getMsgByCode(ex.getCode());
        return new ResponseData<>(ex.getCode(), msg);
    }
}
