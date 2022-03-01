package com.bzfar.service.impl;

import com.bzfar.dto.CourtAnnouncementDto;
import com.bzfar.exception.DataException;
import com.bzfar.service.CourtAnnouncementService;
import com.bzfar.util.AssertUtil;
import com.bzfar.util.DateUtils;
import com.bzfar.utils.MockUtil;
import com.bzfar.vo.AnnouncementVo;
import com.bzfar.vo.CourtAnnouncementVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourtAnnouncementServiceImpl implements CourtAnnouncementService {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<CourtAnnouncementVo> queryCourtAnnouncementInfo(CourtAnnouncementDto courtAnnouncementDto) {
        try {
            SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String beginTimes = courtAnnouncementDto.getBeginTime();
            AssertUtil.assertNull(beginTimes, "开始时间不能为空");
            Date beginTime = sb.parse(beginTimes + " 00:00:00");
            String endTimes = courtAnnouncementDto.getEndTime();
            AssertUtil.assertNull(endTimes, "结束时间不能为空");
            Date endTime = sb.parse(endTimes + " 00:00:00");
            InputStream fileIo = MockUtil.getFileIo("courtAnnouncement.json");
            AnnouncementVo announcementVo = objectMapper.readValue(fileIo, AnnouncementVo.class);
            List<CourtAnnouncementVo> data = announcementVo.getData();
            AssertUtil.assertEmpty(data, "开庭信息为空");
            String date = DateUtils.getDate("yyyy-MM-dd") + " 00:00:00";
            Date today = sb.parse(date);
            List<CourtAnnouncementVo> collect = data.stream().filter(item -> {
                Date courtTime = item.getCourtTime();
                if (courtTime.compareTo(beginTime) >= 0 && courtTime.compareTo(endTime) <= 0) {
                    if (courtTime.compareTo(today) > 0) {
                        item.setStatus("待开庭");
                    }
                    if (courtTime.compareTo(today) < 0) {
                        item.setStatus("已开庭");
                    }
                    if (courtTime.compareTo(today) == 0) {
                        item.setStatus("今日开庭");
                    }
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
            return collect;
        } catch (Exception e) {
            throw new DataException("开庭信息不存在");
        }
    }
}
