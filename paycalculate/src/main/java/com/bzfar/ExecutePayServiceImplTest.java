package com.bzfar;

import com.bzfar.dto.DebitDto;
import com.bzfar.dto.DelayDto;
import com.bzfar.enums.DelayTimeEnum;
import com.bzfar.service.ExecutePayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExecutePayServiceImplTest {

    @Autowired
    private ExecutePayService executePayService;

    @Test
    public void executeCast() {
    }

    @Test
    public void delayCast() {
        DelayDto build = DelayDto.builder()
                .beginTime("2022-03-03")
                .endTime("2022-04-03")
                .delayTime(DelayTimeEnum.YEAR)
                .rate("5")
                .total("1000000").build();
        executePayService.delayCast(build);
    }

    @Test
    public void debitCast() {
        DebitDto build = DebitDto.builder()
                .beginTime("2022-03-03")
                .endTime("2022-04-03")
                .fee("1010000")
                .rate("4.35").build();
        executePayService.debitCast(build);
    }
}