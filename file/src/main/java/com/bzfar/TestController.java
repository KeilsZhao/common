package com.bzfar;

import com.bzfar.enums.FormatConversionEnum;
import com.bzfar.factory.FormatConversionFactory;
import com.bzfar.http.HttpResult;
import com.bzfar.service.FormatConversionService;
import com.bzfar.service.ImageLongService;
import com.bzfar.service.TempFileService;
import com.bzfar.vo.FormatVo;
import com.bzfar.vo.TempleFileVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@RestController
@Api(tags = "测试controller")
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ImageLongService imageLongService;

    @Autowired
    private TempFileService tempFileService;

    @PostMapping("/test")
    @ApiOperation("DDD")
    public HttpResult test(String url) {
        FormatConversionService formatConversionService = FormatConversionFactory.getFormatConversionService(FormatConversionEnum.PDFTOIMG);
        FormatVo pdf = formatConversionService.conversionFormat("pdf", url);
        TreeSet<String> imgUrl = pdf.getImgUrl();
        List<String> paths = new ArrayList<>();
        paths.addAll(imgUrl);
        List<TempleFileVo> templeFileVos = tempFileService.listTempFile(paths);
        FormatVo formatVo = imageLongService.splicingImage(templeFileVos, false);
        System.out.println(formatVo.getHttpUrl());
        return HttpResult.ok(formatVo.getHttpUrl());
    }

}
