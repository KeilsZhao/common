package com.bzfar.service;

import com.bzfar.dto.register.CaseRegisterDto;
import org.springframework.stereotype.Service;

/**
 * 立案已单独抽取新的maven，标准版采用四川简阳的立案，可以直接使用
 */
@Deprecated
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
