package com.bzfar.service;

import com.bzfar.dto.register.CaseRegisterDto;
import org.springframework.stereotype.Service;

@Service
public interface CaseRegisterService {

    /**
     * 网上连申请
     *
     * @param caseRegisterDto 立案申请参数
     * @return 申请结果
     */
    void caseRegister(CaseRegisterDto caseRegisterDto);

}
