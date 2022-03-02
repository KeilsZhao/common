package com.bzfar.service.impl;

import com.bzfar.annotation.CalculateAnn;
import com.bzfar.dto.DebitDto;
import com.bzfar.dto.DelayDto;
import com.bzfar.dto.ExecuteDto;
import com.bzfar.exception.DataException;
import com.bzfar.service.ExecutePayService;
import com.bzfar.service.JiaofeiService;
import com.bzfar.util.AssertUtil;
import com.bzfar.util.DateUtils;
import com.bzfar.vo.DebitVo;
import com.bzfar.vo.DelayPayVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@Service
@Slf4j
public class ExecutePayServiceImpl implements ExecutePayService {

    @Autowired
    private JiaofeiService jiaofeiService;

    @Override
    public List<Object> executeCast(ExecuteDto executeDto) {
        String function = CalculateAnn.methodMap.get(executeDto.getType().getType());
        if (function == null) {
            throw new DataException("没有这种案件计算方式");
        }
        String fee1 = executeDto.getFee();
        if(StringUtils.isBlank(fee1)) {
            fee1 = "0";
        }
        BigDecimal fee = new BigDecimal(fee1);
        List<Object> data = new ArrayList();
        try {
            Method method = jiaofeiService.getClass().getDeclaredMethod(function, BigDecimal.class);
            data = (List<Object>) method.invoke(jiaofeiService, fee);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new DataException("计算错误");
        }
        return data;
    }

    @Override
    public DelayPayVo delayCast(DelayDto delayDto) {
        return null;
    }

    @Override
    public DebitVo debitCast(DebitDto debitDto) {
        Date beginTime = debitDto.getBeginTime();
        AssertUtil.assertNull(beginTime,"开始时间不能为空");
        Date endTime = debitDto.getEndTime();
        AssertUtil.assertNull(endTime,"结束时间不能为空");
        String rate = debitDto.getRate();
        if(StringUtils.isBlank(rate)){
            throw new DataException("费率不能为空");
        }
        String fee = debitDto.getFee();
        if (StringUtils.isBlank(fee)){
            throw new DataException("计算金额不能为空");
        }
        // 获取相差年度
        Integer integer = DateUtils.yearsBetween2(beginTime, endTime);
        return null;
    }
}
