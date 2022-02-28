package com.bzfar.http;

import lombok.Data;

/**
 * controller层返回对象
 * @author ethons
 * @data 2020-08-05
 */
@Data
public class HttpResult<T> {

    /** 返回码 */
    private Integer code;

    /** 返回信息 */
    private String msg;

    /** 返回数据 */
    private T data;

    public static HttpResult error(){
        return error(HttpStatus.BAD_REQUEST.getCode() , "未知异常，请联系管理员");
    }

    public static HttpResult error(String msg){
        return error(HttpStatus.BAD_REQUEST.getCode() , msg);
    }

    public static HttpResult error(String msg , String errorMsg){
        String resultMsg = msg.concat(": ").concat(errorMsg);
        return error(resultMsg);
    }

    public static HttpResult error(int code , String msg){
        HttpResult result = new HttpResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static HttpResult error(HttpStatus status){
        return error(status.getCode() , status.getMsg());
    }

    public static HttpResult error(HttpStatus status , String msg){
        return  error(status.getCode() , msg);
    }

    public static HttpResult ok(String msg){
        HttpResult result = new HttpResult();
        result.setCode(HttpStatus.SUCCESS.getCode());
        result.setMsg(msg);
        return result;
    }

    public static HttpResult ok(Object object){
        HttpResult result = new HttpResult();
        result.setCode(HttpStatus.SUCCESS.getCode());
        result.setMsg(HttpStatus.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    public static HttpResult ok(){
        return ok(HttpStatus.SUCCESS.getMsg());
    }
}
