package com.bzfar.service.impl;

import com.bzfar.dto.CourtAnnouncementDto;
import com.bzfar.service.CourtAnnouncementService;
import com.bzfar.vo.CourtAnnouncementVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CourtAnnouncementServiceImplTest {

    @Autowired
    private CourtAnnouncementService courtAnnouncementService;

    @Test
    public void queryCourtAnnouncementInfo() throws ParseException {
        String beginDate = "2021-03-26";
        String endDate = "2022-03-26";
        CourtAnnouncementDto build = CourtAnnouncementDto.builder()
                .beginTime(beginDate)
                .endTime(endDate)
                .build();
        List<CourtAnnouncementVo> courtAnnouncementVos =
                courtAnnouncementService.queryCourtAnnouncementInfo(build);
        System.out.println(courtAnnouncementVos);
    }
}