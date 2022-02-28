package com.bzfar.service.impl;

import com.bzfar.dto.other.Parent;
import com.bzfar.enums.CaseRegisterEnum;
import com.bzfar.enums.ResponseCode;
import com.bzfar.exception.DataException;
import com.bzfar.service.RegisterCaseService;
import com.bzfar.util.AssertUtil;
import com.bzfar.util.HttpRequestUtils;
import com.bzfar.vo.RegisterCaseVo;
import com.bzfar.vo.qichun.RegisterVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Fimipeler
 */
@Service
public class QiChunRegisterCaseServiceImpl implements RegisterCaseService {

    @Value("${qichun.resisterUrl}")
    private String resisterUrl;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public <T extends Parent> RegisterCaseVo caseRegister(CaseRegisterEnum type, T t) {
        AssertUtil.assertNull(t, "参数信息为空");
        String param = null;
        try {
            param = objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new DataException("参数信息JSON解析错误");
        }
        String result = null;
        try {
            result = HttpRequestUtils.httpPost(resisterUrl, param);
        }catch (Exception e) {
            throw new DataException("发送请求异常（超时或网络异常）");
        }
        RegisterVo registerVo = null;
        try {
            registerVo = objectMapper.readValue(result, RegisterVo.class);
        } catch (JsonProcessingException e) {
            throw new DataException("返回信息解析错误");
        }
        RegisterCaseVo registerCaseVo = RegisterCaseVo.builder()
                .code(ResponseCode.SUCCESS.getCode())
                .msg(registerVo.getMessage())
                .build();
        return registerCaseVo;
    }
}
