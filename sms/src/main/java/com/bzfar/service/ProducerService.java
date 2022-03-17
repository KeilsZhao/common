package com.bzfar.service;

import com.bzfar.dto.QueueDto;


public interface ProducerService {


    void sendMsg(QueueDto queueDto);
}
