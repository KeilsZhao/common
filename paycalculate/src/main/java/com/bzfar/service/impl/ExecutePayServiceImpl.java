package com.bzfar.service.impl;

import com.bzfar.annotation.CalculateAnn;
import com.bzfar.dto.DebitDto;
import com.bzfar.dto.DelayDto;
import com.bzfar.dto.ExecuteDto;
import com.bzfar.enums.DelayTimeEnum;
import com.bzfar.exception.DataException;
import com.bzfar.service.ExecutePayService;
import com.bzfar.service.JiaofeiService;
import com.bzfar.util.AssertUtil;
import com.bzfar.util.DateUtils;
import com.bzfar.vo.DebitVo;
import com.bzfar.vo.DelayPayVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

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
        String function = CalculateAnn.methodMap.get(executeDto.getType());
        if (function == null) {
            throw new DataException("没有这种案件计算方式");
        }
        String fee1 = executeDto.getFee();
        if (StringUtils.isBlank(fee1)) {
            fee1 = "0";
        }
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

    @Override
    public DelayPayVo delayCast(DelayDto delayDto) {
        String total = delayDto.getTotal();
        AssertUtil.assertEmpty(total, "金钱债务额不能为空");
        String execute = delayDto.getExecute();
        DelayTimeEnum delayTime = delayDto.getDelayTime();
        AssertUtil.assertNull(delayTime, "利率单位不能为空");
        String beginTime = delayDto.getBeginTime();
        AssertUtil.assertEmpty(beginTime, "开始时间不能为空");
        String endTime = delayDto.getEndTime();
        AssertUtil.assertEmpty(endTime, "结束时间不能为空");
        long days = DateUtils.getDaysBetween2(beginTime, endTime);
        if (days <= 0) {
            throw new DataException("开始时间必须小于结束时间");
        }
        String rate = delayDto.getRate();
        AssertUtil.assertEmpty(rate, "费率不能为空");
        // 根据费率单位计算日利率
        BigDecimal ratePercent = new BigDecimal(rate).divide(new BigDecimal(100));
        BigDecimal dayRate = new BigDecimal(0);
        switch (delayTime) {
            case YEAR:
                dayRate = ratePercent.divide(new BigDecimal(360), 10, ROUND_HALF_DOWN);
                break;
            case MONTH:
                dayRate = ratePercent.multiply(new BigDecimal(12)).divide(new BigDecimal(360), 10, ROUND_HALF_DOWN);
            case DAY:
                dayRate = ratePercent;
                break;
            default:
                throw new DataException("没有当前利率单位");
        }
        BigDecimal money = new BigDecimal(total);
        if (StringUtils.isNotBlank(execute)) {
            money = money.subtract(new BigDecimal(execute));
        }
        // 一般债务利息
        BigDecimal common = money.multiply(dayRate).multiply(new BigDecimal(days)).setScale(2, RoundingMode.HALF_UP);
        // 加倍部分债务利息
        BigDecimal twice = money.multiply(new BigDecimal(0.000175)).multiply(new BigDecimal(days)).setScale(2, RoundingMode.HALF_UP);
        // 延迟期间债务利息
        BigDecimal delay = common.add(twice);
        return DelayPayVo.builder().common(common.toString()).twice(twice.toString()).delay(delay.toString()).build();
    }

    @Override
    public DebitVo debitCast(DebitDto debitDto) {
        String beginTime = debitDto.getBeginTime();
        AssertUtil.assertEmpty(beginTime, "开始时间不能为空");
        String endTime = debitDto.getEndTime();
        AssertUtil.assertEmpty(endTime, "结束时间不能为空");
        String rate = debitDto.getRate();
        AssertUtil.assertEmpty(rate, "费率不能为空");
        String fee = debitDto.getFee();
        AssertUtil.assertEmpty(fee, "计算金额不能为空");
        // 获取相差天
        long days = DateUtils.getDaysBetween2(beginTime, endTime);
        // 计算利率
        BigDecimal rates = new BigDecimal(rate);
        BigDecimal ratePercent = rates.divide(new BigDecimal(100));
        BigDecimal multiply = new BigDecimal(fee).multiply(ratePercent.divide(new BigDecimal(360), 10, ROUND_HALF_DOWN)).multiply(new BigDecimal(days)).setScale(2, RoundingMode.HALF_UP);
        DebitVo build = DebitVo.builder().debit(multiply.toString()).build();
        return build;
    }
}
