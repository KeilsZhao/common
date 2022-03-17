package com.bzfar.service.impl;


import com.bzfar.dto.QueueDto;
import com.bzfar.exception.DataException;
import com.bzfar.middleware.RabbitProducer;
import com.bzfar.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    RabbitProducer rabbitProducer;

    @Override
    public void sendMsg(QueueDto queueDto) {
        try{
            rabbitProducer.sendMessage("confirmTestExchange","2",queueDto);
        }catch (Exception e){
            throw new DataException("消息队列异常");
        }

    }
}
