package com.bzfar.service.impl;

import com.bzfar.dto.ConnectDto;
import com.bzfar.dto.ConnectJudgeDto;
import com.bzfar.exception.DataException;
import com.bzfar.service.ConnectJudgeService;
import com.bzfar.util.AssertUtil;
import com.bzfar.utils.MockUtil;
import com.bzfar.vo.ConnectJudgeCaseVo;
import com.bzfar.vo.ConnectVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConnectJudgeServiceImpl implements ConnectJudgeService {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<ConnectJudgeCaseVo> queryConnectJudgeCase(ConnectJudgeDto connectJudgeDto) {
        AssertUtil.assertNull(connectJudgeDto.getSfzh(), "身份证号不能为空");
        try {
            InputStream fileIo = MockUtil.getFileIo("connectJudgeCase.json");
            ConnectVo connectVo = objectMapper.readValue(fileIo, ConnectVo.class);
            List<ConnectJudgeCaseVo> data = connectVo.getData();
            AssertUtil.assertNull(data,"案件信息为空");
            List<ConnectJudgeCaseVo> collect = data.stream().filter(item -> item.getSfzh().equals(connectJudgeDto.getSfzh())).collect(Collectors.toList());
            AssertUtil.assertNull(collect,"当前身份证下案件信息为空");
            return collect;
        } catch (Exception e) {
            throw new DataException("案件信息不存在");
        }
    }

    @Override
    public void connectJudge(ConnectDto connectDto) {

    }
}
