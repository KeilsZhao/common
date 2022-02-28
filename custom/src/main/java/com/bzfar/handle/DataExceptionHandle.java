package com.bzfar.handle;

import com.bzfar.exception.DataException;
import com.bzfar.http.HttpResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.stream.Collectors;

/**
 * 异常全局拦截
 * @author 刘成
 * @date 2021-5-6
 */
@ControllerAdvice
public class DataExceptionHandle {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @ExceptionHandler
    @ResponseBody
    public HttpResult handle(ValidationException exception) {
        return HttpResult.error(exception.getMessage());
    }


    @ExceptionHandler(value = DataException.class)
    @ResponseBody
    public HttpResult dataException(DataException e){
        if(ObjectUtils.isEmpty(e.getCode())){
            return HttpResult.error(e.getMessage());
        }else{
            return HttpResult.error(e.getCode() , e.getMessage());
        }
    }


    @ExceptionHandler(BindException.class)
    @ResponseBody
    public HttpResult BindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return HttpResult.error(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public HttpResult MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return HttpResult.error(message);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public HttpResult ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return HttpResult.error(message);
    }
}
