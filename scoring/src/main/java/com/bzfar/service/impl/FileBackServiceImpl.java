package com.bzfar.service.impl;

import com.bzfar.configuration.FileBackConfig;
import com.bzfar.dto.FileBackDto;
import com.bzfar.dto.FileBackFtpDto;
import com.bzfar.dto.FileBackSerialDto;
import com.bzfar.exception.DataException;
import com.bzfar.service.FileBackService;
import com.bzfar.util.DateUtils;
import com.bzfar.util.RandomUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * @author Fimipeler
 * @Description FileBackServiceImpl
 * @Date 2021/6/9 18:28
 */
@Service
@Slf4j
public class FileBackServiceImpl implements FileBackService {

    @Autowired
    private XmlMapper xmlMapper;

    @Autowired
    private FileBackConfig fileBackConfig;

    @Override
    public String getSerialNumber(FileBackSerialDto fileBackDto) {
        log.info("【request params】:" + fileBackDto);
        String requestInfo = null;
        try {
            String xmlInfo = xmlMapper.writeValueAsString(fileBackDto);
            log.info("【translate to xml】:" + xmlInfo);
            requestInfo = Base64.getEncoder().encodeToString(xmlInfo.getBytes());

//        String result = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><case><response><code>MA==</code><lsh>MjAyMTA2MDlIMTAwMDAwMw==</lsh></response></case>";
//        FileBackSerialVo fileBackSerialVo = xmlMapper.readValue(result, FileBackSerialVo.class);
        } catch (JsonProcessingException e) {
            throw new DataException(e.getMessage());
        }

        //模拟数据返回一个流水码
        String date = DateUtils.getDate("yyyyMMdd");
        int num = RandomUtils.genNumByLen(6);
        StringBuilder sb = new StringBuilder();
        sb.append(date).append("H1").append(num);
        return sb.toString();
    }


    @Override
    public String putFileInfo(FileBackDto fileBackDto) {
        FileBackFtpDto fileBackFtpDto = FileBackFtpDto.builder()
                .filePath("/")
                .ip(fileBackConfig.getFtpHost())
                .port(fileBackConfig.getFtpPort())
                .user(fileBackConfig.getFtpUsername())
                .password(fileBackConfig.getFtpPassword()).build();
        fileBackDto.setFtpDto(fileBackFtpDto);

        log.info("【file Info】:"+fileBackDto);
        String requestInfo = null;
        try {
            String xmlInfo = xmlMapper.writeValueAsString(fileBackDto);
            log.info("【file info xml】:"+xmlInfo);
            requestInfo = Base64.getEncoder().encodeToString(xmlInfo.getBytes());

//            String result = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><case><response><code>MA==</code><errmsg></errmsg></response></case>";
//            FileBackInfoVo fileBackInfoVo = xmlMapper.readValue(result, FileBackInfoVo.class);
//            log.info("【file info response】:"+fileBackInfoVo);
        } catch (JsonProcessingException e) {
            throw new DataException(e.getMessage());
        }


        return "材料上传成功";

    }
}
