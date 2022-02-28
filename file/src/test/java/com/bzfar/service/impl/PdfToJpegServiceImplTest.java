package com.bzfar.service.impl;

import com.bzfar.enums.FormatConversionEnum;
import com.bzfar.factory.FormatConversionFactory;
import com.bzfar.service.FormatConversionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class PdfToJpegServiceImplTest {

    @Test
    void conversionFormat() throws FileNotFoundException {
        String path = "C:\\Users\\Fimipeler\\Desktop\\格式转换\\一体机对接文档.pdf";
        FormatConversionService formatConversionService = FormatConversionFactory.getFormatConversionService(FormatConversionEnum.PDFTOJPG);
        formatConversionService.conversionFormat("pdf",path);
    }
}