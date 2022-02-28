package com.bzfar.service;


import com.bzfar.ws.SendSms;
import com.bzfar.ws.SendSmsResponse;
import com.bzfar.ws.SmsObject;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * 外网发送短信采用的wsdl方式
 */
public class WsClient extends WebServiceGatewaySupport {

    public SendSmsResponse sendMsm(String userName , String passWord, String url , String phone , String context){
        SmsObject smsObject = new SmsObject();
        smsObject.setMsisdns(phone);
        smsObject.setSMSContent(context);
        SendSms request = new SendSms();
        request.setUserName(userName);
        request.setPassword(passWord);
        request.setSms(smsObject);
        SoapActionCallback soapActionCallback = new SoapActionCallback(url);
        SendSmsResponse response = (SendSmsResponse)getWebServiceTemplate().marshalSendAndReceive(request , soapActionCallback);
        return response;
    }
}
