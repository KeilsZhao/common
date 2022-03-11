package com.bzfar.service.impl;


import com.bzfar.exception.DataException;
import com.bzfar.service.HandleSms;
import com.bzfar.service.SmsService;
import com.bzfar.service.way.ExtranetSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
