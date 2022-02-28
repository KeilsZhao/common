package com.bzfar.service;

import com.bzfar.enums.CourtEnum;
import com.bzfar.vo.DossierVo;

import java.util.List;

/**
 * @author 刘成
 * @date 2021-6-3
 */
public interface ScoringService {

    /**
     * 查询案件对应卷宗目录
     * @param courtEnum 第三方厂商
     * @param ah 案号
     * @return
     */
    List<DossierVo> getDossierCatalog(CourtEnum courtEnum , String ah);



}
