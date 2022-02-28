package com.bzfar.exception;

/**
 * 数据校验异常
 * @author ethons
 * @data 2020-07-05
 */
public class ValidateException extends RuntimeException {

    public ValidateException(String message) {
        super(message);
    }
}
