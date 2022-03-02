package com.bzfar.service;

import com.bzfar.dto.CalculateDto;
import com.bzfar.dto.DebitDto;
import com.bzfar.dto.DelayDto;
import com.bzfar.dto.ExecuteDto;
import com.bzfar.vo.DebitVo;
import com.bzfar.vo.DelayPayVo;

import java.util.List;

/**
 * 执行案件缴费计算
 * @author Administrator
 */
public interface ExecutePayService {

    /**
     * 执行费用计算
     *
     * @param calculateDto 计算参数
     * @return
     */
    List<Object> executeCast(ExecuteDto executeDto);

    /**
     * 延迟执行费用计算
     *
     * @param delayDto 执行参数
     * @return
     */
    DelayPayVo delayCast(DelayDto delayDto);

    /**
     * 借贷计算器计算
     *
     * @param debitDto 借贷计算器参数
     * @return
     */
    DebitVo debitCast(DebitDto debitDto);


}
