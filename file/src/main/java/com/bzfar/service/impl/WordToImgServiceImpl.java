package com.bzfar.service.impl;

import com.aspose.words.Document;
import com.aspose.words.ImageSaveOptions;
import com.aspose.words.SaveFormat;
import com.bzfar.service.FormatConversionService;
import com.bzfar.service.TempFileService;
import com.bzfar.utils.AsposeLicenseUtil;
import com.bzfar.utils.PathUtil;
import com.bzfar.vo.FormatVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.TreeSet;

/**
 * @author ""
 * @Description WordToImgServiceImpl
 * @Date 2021/12/10 11:45
 */
@Service
@Slf4j
public class WordToImgServiceImpl implements FormatConversionService {

    @Autowired
    private TempFileService tempFileService;

    @Autowired
    private PathUtil pathUtil;

    @Override
    public FormatVo conversionFormat(String oldType, String sourceFilePath) {
        if (!AsposeLicenseUtil.wordLicense()) {
            return null;
        }
        InputStream fileInputStream = tempFileService.getFileStream(sourceFilePath);
        TreeSet<String> imgUrl = new TreeSet<>();
        try {
            Document doc = new Document(fileInputStream);
            ImageSaveOptions iso = new ImageSaveOptions(SaveFormat.JPEG);
            iso.setPrettyFormat(true);
            iso.setUseAntiAliasing(true);
            iso.setJpegQuality(80);
            for (int i = 0; i < doc.getPageCount(); i++) {
                Long startTs = System.currentTimeMillis();
                String imgName = startTs + "_" + i + ".jpg";
                iso.setPageIndex(i);
                doc.save(pathUtil.concatStore(imgName), iso);
                imgUrl.add(pathUtil.concatHttp(imgName));
            }
        } catch (Exception e) {
            log.error("【word转图片错误】 = {}", e.getMessage());
        } finally {
            try {
                if (null != fileInputStream) {
                    fileInputStream.close();
                }
            } catch (Exception e) {
                log.error("【word转图片流关闭异常】 = {}", e.getMessage());
            }
        }
        return FormatVo.builder().imgUrl(imgUrl).build();
    }
}
