package com.bzfar.service.impl;

import com.bzfar.annotation.CalculateAnn;
import com.bzfar.dto.CalculateDto;
import com.bzfar.exception.DataException;
import com.bzfar.service.JiaofeiService;
import com.bzfar.service.PayCalculateService;
import com.bzfar.util.PatternUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PayCalculateServiceImpl implements PayCalculateService {

    @Autowired
    private JiaofeiService jiaofeiService;

    @Override
    @SneakyThrows(DataException.class)
    public List<Object> calculate(CalculateDto calculateDto) {
        String function = CalculateAnn.methodMap.get(calculateDto.getType());
        if (function == null) {
            throw new DataException("没有这种案件计算方式");
        }
        String fee1 = calculateDto.getFee();
        if (StringUtils.isBlank(fee1)) {
            fee1 = "0";
        }
        PatternUtil.checkNum(fee1, "请输入正确的金额");
        BigDecimal fee = new BigDecimal(fee1);
        List<Object> data = new ArrayList();
        try {
            Method method = jiaofeiService.getClass().getDeclaredMethod(function, BigDecimal.class);
            data = (List<Object>) method.invoke(jiaofeiService, fee);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new DataException("计算错误");
        }
        return data;
    }
}
