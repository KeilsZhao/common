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
public class PdfToImgServiceImplTest {

    @Test
    public void conversionFormat() {
        String path = "C:\\Users\\Administrator\\Desktop\\自助机对接网上立案接口文档.pdf";
        FormatConversionService formatConversionService = FormatConversionFactory.getFormatConversionService(FormatConversionEnum.PDFTOIMG);
        formatConversionService.conversionFormat("pdf",path);
    }
}