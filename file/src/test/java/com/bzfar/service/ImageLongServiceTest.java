package com.bzfar.service;

import com.bzfar.enums.FormatConversionEnum;
import com.bzfar.factory.FormatConversionFactory;
import com.bzfar.vo.FormatVo;
import com.bzfar.vo.TempleFileVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ImageLongServiceTest {

    @Autowired
    private ImageLongService imageLongService;

    @Autowired
    private TempFileService tempFileService;

    @Test
    public void splicingImage() {
        String path = "C:\\Users\\Administrator\\Desktop\\maven使用手册.pdf";
        FormatConversionService formatConversionService = FormatConversionFactory.getFormatConversionService(FormatConversionEnum.PDFTOIMG);
        FormatVo pdf = formatConversionService.conversionFormat("pdf", path);
        TreeSet<String> imgUrl = pdf.getImgUrl();
        List<String> paths = new ArrayList<>();
        paths.addAll(imgUrl);
        List<TempleFileVo> templeFileVos = tempFileService.listTempFile(paths);
        FormatVo formatVo = imageLongService.splicingImage(templeFileVos,false);
        System.out.println(formatVo.getHttpUrl());
    }

    @Test
    public void test() {
        String path = "C:\\Users\\Administrator\\Desktop\\智能导诉系统详设.doc";
        FormatConversionService formatConversionService = FormatConversionFactory.getFormatConversionService(FormatConversionEnum.WRORDTOIMG);
        FormatVo pdf = formatConversionService.conversionFormat("doc", path);
        TreeSet<String> imgUrl = pdf.getImgUrl();
        List<String> paths = new ArrayList<>();
        paths.addAll(imgUrl);
        List<TempleFileVo> templeFileVos = tempFileService.listTempFile(paths);
        FormatVo formatVo = imageLongService.splicingImage(templeFileVos,false);
        System.out.println(formatVo.getHttpUrl());
    }
}