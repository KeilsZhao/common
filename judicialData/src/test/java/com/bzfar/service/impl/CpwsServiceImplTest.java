package com.bzfar.service.impl;

import com.bzfar.dto.CpwsDto;
import com.bzfar.service.CpwsService;
import com.bzfar.vo.CpwsInfoVo;
import com.bzfar.vo.CpwsVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CpwsServiceImplTest {

    @Autowired
    private CpwsService cpwsService;

    @Test
    public void getCpwsList() {
        List<CpwsVo> list = cpwsService.getCpwsList(CpwsDto.builder().num(10).condition("北京市法院").build());
        log.info("【data】:{}",list);
    }


    @Test
    public void getCpwsById() {
        CpwsInfoVo cpwsById = cpwsService.getCpwsById("99072224");
        log.info("【data】:{}",cpwsById);
    }
}