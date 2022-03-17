package com.bzfar.service.impl;


import com.bzfar.enums.BaseEnum;
import com.bzfar.enums.NewSmsEnum;
import com.bzfar.enums.OldSmsEnum;
import com.bzfar.exception.DataException;
import com.bzfar.service.HandleSms;
import com.bzfar.service.SmsService;
import com.bzfar.service.way.ExtranetSms;
import com.bzfar.util.HttpUrlSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;

@Service
public class SmsServiceImpl implements SmsService {



    @Override
    public void sendSms(String phone, String context) {
        try{
            HandleSms sms = new ExtranetSms();
            sms.handleSms(phone , context);
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
    }

    @Override
    public void sendSms(BaseEnum baseEnum, @NotBlank(message = "电话不能为空") String phone, @NotBlank(message = "短信内容不能为空") String context) {
        if(baseEnum instanceof NewSmsEnum){
            HttpUrlSend.sendGet(phone, context);
        }else if (baseEnum instanceof OldSmsEnum){
            sendSms( phone, context);
        }else{
            throw new DataException("不存在此短信模板");
        }
    }

//    private String getContent(String word){
//        NewSmsEnum.back_msg.getContext();
//        context.replaceAll("//{案号,30}" , "ah").replaceAll("{姓名,20}" , "name");
//
//    }
//    private String replace(){
//        String context = DemoEnum.login_code.getContext();
//        context.replaceAll("//{案号,30}" , "ah").replaceAll("{姓名,20}" , "name");
//    }


//    @Override
//    public String sendSms(String phone , String context) {
//        String s = "";
//        try{
//            HandleSms sms = new ExtranetSms();
//            s = sms.handleSms(phone , context);
//        }catch (Exception e){
//            throw new DataException(e.getMessage());
//        }
//        return s;
//    }
//
//    @Override
//    public void sendMqSms(String phone, String context) {
//        try{
//            rabbitProducer.sendDemoQueue(phone , context);
//        }catch (Exception e){
//            throw new DataException(e.getMessage());
//        }
//    }
}
