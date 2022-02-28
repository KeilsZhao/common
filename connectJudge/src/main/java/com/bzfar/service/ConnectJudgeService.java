package com.bzfar.service;

import com.bzfar.dto.ConnectDto;
import com.bzfar.dto.ConnectJudgeDto;
import com.bzfar.vo.ConnectJudgeCaseVo;

import java.util.List;

public interface ConnectJudgeService {


    /**
     * 获取开庭信息
     *
     * @param connectJudgeDto 查询信息
     * @return
     */
    List<ConnectJudgeCaseVo> queryConnectJudgeCase(ConnectJudgeDto connectJudgeDto);


    /**
     * 根据流水号获取承办人信息
     *
     * @param connectDto 短信内容
     */
    void connectJudge(ConnectDto connectDto);
}
