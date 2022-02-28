package com.bzfar.service;

import com.bzfar.dto.AnnexDto;
import com.bzfar.dto.EmailDTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.annotation.Validated;

import java.util.List;

public interface EmailService {

    /**
     * 发送携带附件的邮件
     * @param dto 参数
     * @param annexList 附件集合
     */
    @Async
    void sendEmail(@Validated EmailDTO dto , List<AnnexDto> annexList);

    /**
     * 发送邮件
     * @param dto 参数
     */
    @Async
    void semdEmail(@Validated EmailDTO dto);
}
