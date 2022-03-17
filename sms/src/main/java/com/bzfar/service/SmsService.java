package com.bzfar.service;

import com.bzfar.enums.BaseEnum;
import org.springframework.scheduling.annotation.Async;

import javax.validation.constraints.NotBlank;


public interface SmsService {

    /**
     * 发送短信
     * @param phone 手机号码
     * @param context 短信内容
     */
    @Async
    void sendSms(@NotBlank(message = "电话不能为空") String phone , @NotBlank(message = "短信内容不能为空") String context);


    /**
     * 发送短信
     * @param phone 手机号码
     * @param context 短信内容
     */
//    @Async
    void sendSms(BaseEnum baseEnum, @NotBlank(message = "电话不能为空") String phone , @NotBlank(message = "短信内容不能为空") String context);
}
