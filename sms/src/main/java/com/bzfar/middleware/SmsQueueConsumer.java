package com.bzfar.middleware;//package com.bzfar.controller;

import com.bzfar.dto.SmsDto;
import com.bzfar.exception.DataException;
import com.bzfar.service.SmsService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@Component
@RabbitListener(queues = "confirm_test_queue")
public class SmsQueueConsumer {

    @Autowired
    private SmsService smsService;

    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */

    @RabbitHandler
    public void processHandler(SmsDto smsDto, Channel channel, Message message) throws IOException {

        try {
            log.info("收到消息：{}", smsDto);
            //TODO 具体业务
            smsService.sendSms(smsDto.getPhoneNumber(),smsDto.getContext());
//            手动确认消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        }  catch (Exception e) {

            if (message.getMessageProperties().getRedelivered()) {

                log.error("消息已重复处理失败,拒绝再次接收...");

                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
            } else {

                log.error("消息即将再次返回队列处理...");

                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

}