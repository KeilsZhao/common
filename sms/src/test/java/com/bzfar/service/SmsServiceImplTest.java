package com.bzfar.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author 刘成
 * @date 2021-5-13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SmsServiceImplTest {

    @Autowired
    private SmsService smsService;

    @Test
    public void sendMqSms() {
       // smsService.sendMqSms("18666207975" , "【百智诚远】您的验证码为：123请勿将验证码透露给任何人!");
    }
}