package com.bzfar.service.impl;

import com.bzfar.enums.FormatConversionEnum;
import com.bzfar.factory.FormatConversionFactory;
import com.bzfar.service.FormatConversionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
class TifToJpegServiceImplTest {

    @Test
    void conversionFormat() throws FileNotFoundException {
        String path = "C:\\Users\\Fimipeler\\Desktop\\测试格式转换\\tif.tif";
        FormatConversionService formatConversionService = FormatConversionFactory.getFormatConversionService(FormatConversionEnum.TIFTOJPEG);
        formatConversionService.conversionFormat("tif",path);
    }
}