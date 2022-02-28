package com.bzfar.service;

import com.bzfar.dto.WaitInfoDto;
import com.bzfar.vo.WaitInfoVo;

import java.util.List;

/**
 * 开庭排期接口，已开发新的maven，标准版模拟数据，可直接使用
 */
@Deprecated
public interface WaitInfoService {

    /**
     * 拉取第三方获取开庭排期信息
     * @param dto
     * @return
     */
    List<WaitInfoVo> queryCourtAnnouncement(WaitInfoDto dto);



}
