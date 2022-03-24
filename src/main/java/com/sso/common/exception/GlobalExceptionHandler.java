package com.sso.common.exception;

import com.sso.common.ResponseData;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

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
    public ResponseData<Object> bizExceptionHandler(BizException ex) {
        log.error("发生接口调用异常 原因是:", ex);
        String msg = null != (ex.getMessage()) ? ex.getMessage() : ResponseCodeEnum.getMsgByCode(ex.getCode());
        return new ResponseData<>(ex.getCode(), msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseData<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining());
        return new ResponseData<>(ResponseCodeEnum.PARAM_INVALID.getCode(), message);
    }
}
