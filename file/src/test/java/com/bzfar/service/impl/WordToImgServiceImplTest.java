package com.bzfar.service.impl;

import com.bzfar.enums.FormatConversionEnum;
import com.bzfar.factory.FormatConversionFactory;
import com.bzfar.service.FormatConversionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WordToImgServiceImplTest {

    @Test
    public void conversionFormat() {
        String path = "C:\\Users\\Administrator\\Desktop\\20210916诉讼服务智能自助系统-操作手册.docx";
        FormatConversionService formatConversionService = FormatConversionFactory.getFormatConversionService(FormatConversionEnum.WRORDTOIMG);
        formatConversionService.conversionFormat("docx",path);
    }
}