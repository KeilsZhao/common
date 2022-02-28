package com.bzfar.service;

import com.bzfar.dto.other.Parent;
import com.bzfar.enums.CaseRegisterEnum;
import com.bzfar.vo.RegisterCaseVo;
import org.springframework.stereotype.Service;

/**
 * @author Fimipeler
 */
@Service
public interface RegisterCaseService {

    /**
     * 立案接口
     * @param type 代理类型
     * @param t 参数
     * @return
     */
    <T extends Parent> RegisterCaseVo caseRegister(CaseRegisterEnum type, T t);

}
