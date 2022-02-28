package com.bzfar.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;


/**
 * @author Ethons
 * @date 2021-6-29 11:22
 */
@Configuration
@Data
public class MailConfiguration {

    /** 发送邮件服务器 */
    public final String host = "smtp.exmail.qq.com";
    /** 百智企业邮箱 */
    public final String userName = "service@bzfar.com";
    /** 客户端授权码 */
    public final String passWord = "syhMrRw6Df8D6vVs";
    /** 发送邮件协议 */
    public final String protocol = "smtp";
    /** 编码格式 */
    public final String encoding = "utf-8";


    @Bean
    public JavaMailSenderImpl JavaMailSenderImpl(){
        JavaMailSenderImpl mail = new JavaMailSenderImpl();
        mail.setHost(host);
        mail.setUsername(userName);
        mail.setPassword(passWord);
        mail.setProtocol(protocol);
        mail.setDefaultEncoding(encoding);
        return mail;
    }
}
