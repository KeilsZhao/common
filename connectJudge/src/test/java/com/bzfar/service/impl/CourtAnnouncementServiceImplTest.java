package com.bzfar.service.impl;

import com.bzfar.dto.ConnectJudgeDto;
import com.bzfar.service.ConnectJudgeService;
import com.bzfar.vo.ConnectJudgeCaseVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CourtAnnouncementServiceImplTest {

    @Autowired
    private ConnectJudgeService connectJudgeService;

    @Test
    public void queryCourtAnnouncementInfo() throws ParseException {

        ConnectJudgeDto build = ConnectJudgeDto.builder()
                .sfzh("422801199306123301")
                .build();
        List<ConnectJudgeCaseVo> connectJudgeVos =
                connectJudgeService.queryConnectJudgeCase(build);
        System.out.println(connectJudgeVos);
    }
}