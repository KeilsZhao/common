package com.bzfar.service.impl;

import com.bzfar.dto.register.CaseRegisterDto;
import com.bzfar.service.CaseRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 立案接口
 * @author Fimipeler
 */
@Service
@Slf4j
public class CaseRegisterServiceImpl implements CaseRegisterService {


    @Override
    public void caseRegister(CaseRegisterDto caseRegisterDto) {
        log.info(caseRegisterDto.toString());
        // 调用第三方接口进行网上立案
    }
}
