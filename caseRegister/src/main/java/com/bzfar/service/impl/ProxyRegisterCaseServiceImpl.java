package com.bzfar.service.impl;

import com.bzfar.dto.other.Parent;
import com.bzfar.enums.CaseRegisterEnum;
import com.bzfar.service.RegisterCaseService;
import com.bzfar.utils.ProxyBeanUtil;
import com.bzfar.vo.RegisterCaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author Fimipeler
 */
@Service
@Primary
public class ProxyRegisterCaseServiceImpl implements RegisterCaseService {

    @Autowired
    private ProxyBeanUtil<RegisterCaseService> proxyBeanUtil;

    @Override
    public <T extends Parent> RegisterCaseVo caseRegister(CaseRegisterEnum type, T t){
        return proxyBeanUtil.target(this.getClass(),type).caseRegister(type,t);
    }
}
