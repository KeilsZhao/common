package com.bzfar.exception;

import com.bzfar.http.HttpStatus;
import com.bzfar.enums.ResponseCode;
import lombok.Data;

/**
 * 数据错误异常
 * @author Ethons
 * @date 2020-08-05
 */
@Data
public class DataException extends RuntimeException {

    private Integer code;

    private String message;

    public DataException(){
        this("数据已存在");
    }

    public DataException(ResponseCode responseCode) {
        this(responseCode.getCode(),responseCode.getMsg());
    }

    public DataException(String message) {
        this(ResponseCode.THIRD_ERROR.getCode(),message);
    }

    public DataException(ResponseCode responseCode , String message) {
        this(responseCode.getCode(),message);
    }

    public DataException(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public DataException(HttpStatus httpStatus){
        this(httpStatus.getCode(), httpStatus.getMsg());
    }
}
