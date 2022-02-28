package com.bzfar.service;

import com.bzfar.dto.CaseBatchDto;
import com.bzfar.vo.CaseBatchVo;

import java.util.List;

public interface CaseBatchService {

    /**
     * 第三方案件批量下载接口
     * @param dto
     * @return
     */
    List<CaseBatchVo> queryCaseBatchByThird(CaseBatchDto dto);

}
