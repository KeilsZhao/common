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
        String beginDate = "2021-01-02 01:20:00";
        String endDate = "2022-01-02 01:20:00";
        SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin = sb.parse(beginDate);
        Date end = sb.parse(endDate);
        CourtAnnouncementDto build = CourtAnnouncementDto.builder()
                .beginTime(begin)
                .endTime(end)
                .build();
        List<CourtAnnouncementVo> courtAnnouncementVos =
                courtAnnouncementService.queryCourtAnnouncementInfo(build);
        System.out.println(courtAnnouncementVos);
    }
}