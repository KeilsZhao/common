package com.bzfar.service;

import com.bzfar.dto.CalculateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PayCalculateService {

    /**
     * 缴费计算
     *
     * @param calculateDto 参数
     * @return
     */
    List<Object> calculate(CalculateDto calculateDto);

}
