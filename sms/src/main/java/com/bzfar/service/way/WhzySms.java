package com.bzfar.service.way;

import com.bzfar.service.HandleSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 武汉中院使用的内网短信
 */
public class WhzySms extends HandleSms {

    @Autowired
    private RestTemplate restTemplate;

    /** 短信ip地址 */
    private final static String ip = "144.4.2.124";
    /** 短信请求地址 */
    private final static String url = "http://144.4.2.124:8897/sms/Api/Send.do";
    /** 企业码 */
    private final static String spCode = "210145";
    /** 用户名 */
    private final static String loginName = "wh_zj";
    /** 密码 */
    private final static String passWord = "rmfy2001";

    public WhzySms() {
        super(ip);
    }

    @Override
    protected String sendSms(String phone , String context) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("SpCode", spCode);
        map.add("LoginName", loginName);
        map.add("Password", passWord);
        map.add("MessageContent", context);
        map.add("UserNumber", phone);
        map.add("SerialNumber", "");
        map.add("ScheduleTime", "");
        map.add("f", "1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/x-www-form-urlencoded;charset=GB2312"));
        HttpEntity entity = new HttpEntity(map, headers);
        String s = restTemplate.postForObject(url, entity, String.class);
        return "内网短信发送成功";
    }
}
