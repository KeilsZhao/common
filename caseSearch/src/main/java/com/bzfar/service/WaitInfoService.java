package com.bzfar.service;

import com.bzfar.dto.WaitInfoDto;
import com.bzfar.vo.WaitInfoVo;

import java.util.List;

public interface WaitInfoService {

    /**
     * 拉取第三方获取开庭排期信息
     * @param dto
     * @return
     */
    List<WaitInfoVo> queryCourtAnnouncement(WaitInfoDto dto);



}
