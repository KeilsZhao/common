package com.bzfar.service;

import com.bzfar.dto.CourtAnnouncementDto;
import com.bzfar.vo.CourtAnnouncementVo;

import java.util.List;

public interface CourtAnnouncementService {


    /**
     * 获取开庭信息
     *
     * @param courtAnnouncementDto 查询信息
     * @return
     */
    List<CourtAnnouncementVo> queryCourtAnnouncementInfo(CourtAnnouncementDto courtAnnouncementDto);
}
