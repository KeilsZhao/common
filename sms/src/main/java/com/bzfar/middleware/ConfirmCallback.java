package com.bzfar.middleware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//ConfirmCallback确认模式
//消息只要被 rabbitmq broker 接收到就会触发 confirmCallback 回调 。
public class ConfirmCallback implements RabbitTemplate.ConfirmCallback {

    @Override
//    correlationData：对象内部只有一个 id 属性，用来表示当前消息的唯一性。
//    ack：消息投递到broker 的状态，true表示成功。
//    cause：表示投递失败的原因。
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        if (!ack) {
            log.error("消息发送异常!correlationData={} ,ack={}, cause={}", correlationData.getId(), ack, cause);
        } else {
            log.info("发送者已经收到确认，correlationData={} ,ack={}, cause={}", correlationData.getId(), ack, cause);
        }
    }
}

