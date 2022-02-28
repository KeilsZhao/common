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

@SpringBootTest
@RunWith(SpringRunner.class)
class WordToPdfServiceImplTest {


    @Test
    void conversionFormat() throws FileNotFoundException {
        String path = "C:\\Users\\Fimipeler\\Desktop\\1639041628492_57940.doc";
        FormatConversionService formatConversionService = FormatConversionFactory.getFormatConversionService(FormatConversionEnum.WORDTOPDF);
        formatConversionService.conversionFormat("doc",path);
    }
}