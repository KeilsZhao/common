package com.bzfar.service.way;

import com.bzfar.exception.DataException;
import com.bzfar.service.HandleSms;
import com.bzfar.service.WsClient;
import com.bzfar.ws.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * 外网短信
 */
@Slf4j
public class ExtranetSms extends HandleSms {

    private final static String ip = "dx.ipyy.net";

    private final static String url = "http://www.yysms.com/SendSms";

    private final static String userName = "yunnanyungui";

    private final static String passWord = "123456";


    public ExtranetSms() {
        super(ip);
        setNextHandle(new WhzySms());
    }

    @Override
    protected String sendSms(String phone , String context) {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.bzfar.ws");
        WsClient client = new WsClient();
        client.setDefaultUri("https://dx.ipyy.net/webservice.asmx?wsdl");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        SendSmsResponse response = client.sendMsm(userName, passWord, url, phone, context);
        SendResult sendSmsResult = response.getSendSmsResult();
        if(!sendSmsResult.getStatusCode().equals(ResultCode.fromValue("OK"))){
            log.error("【发送信息失败】e = {}" , sendSmsResult.getDescription());
            throw new DataException(sendSmsResult.getDescription());
        }
        return response.getSendSmsResult().getDescription();
    }
}
