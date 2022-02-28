package com.bzfar.service.impl;

import com.bzfar.dto.EmailDTO;
import com.bzfar.service.EmailService;
import com.bzfar.service.HandleSms;
import com.bzfar.service.SmsService;
import com.bzfar.service.way.ExtranetSms;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class EmailServiceImplTest {

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    @Test
    public void semdEmail() {
        emailService.semdEmail(
                EmailDTO.builder()
                        .to("563154644@qq.com")
                        .subject("测试")
                        .text("测试")
                .build());
    }

    @Test
    public void sendSms(){
        smsService.sendSms("18666207975" , "【百智诚远】您的验证码为：123123123请勿将验证码透露给任何人!");
    }
}