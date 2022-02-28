package com.bzfar.service;

import com.bzfar.entity.DataAiBean;
import org.springframework.stereotype.Service;

/**
 * @author Fimipeler
 * @Description GuidanceService
 * @Date 2021/6/16 10:35
 */
@Service
public interface GuidanceService {

    /**
     * 导诉咨询问答
     *
     * @param questtion 问题
     * @return
     */
    DataAiBean askQuestion(String questtion);

}
