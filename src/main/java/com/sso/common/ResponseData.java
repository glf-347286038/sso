package com.sso.common;

import com.sso.common.exception.ResponseCodeEnum;
import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;

/**
 * 返回信息统一格式
 *
 * @author: golf
 * @date: 2022-03-17 20:11
 */
@Data
public class ResponseData<T> implements Serializable {
    private static final long serialVersionUID = -8137885488837998490L;
    /**
     * 是否成功,默认true
     */
    private Boolean success = true;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public static <T> ResponseData<T> success(T data) {
        ResponseData<T> result = new ResponseData<>(ResponseCodeEnum.SUCCESS);
        result.setData(data);
        return result;
    }

    public ResponseData(ResponseCodeEnum responseCodeEnum) {
        this.code = responseCodeEnum.getCode();
        this.message = responseCodeEnum.getMsg();
    }

    public ResponseData(Integer code, String message) {
        this.success = Boolean.FALSE;
        this.code = code;
        this.message = message;
    }
}
