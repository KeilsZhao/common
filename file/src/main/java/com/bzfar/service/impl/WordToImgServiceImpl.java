package com.bzfar.service.impl;

import com.aspose.words.License;
import com.aspose.words.Document;
import com.aspose.words.ImageSaveOptions;
import com.aspose.words.SaveFormat;
import com.bzfar.config.FileConfig;
import com.bzfar.service.FormatConversionService;
import com.bzfar.service.TempFileService;
import com.bzfar.util.AssertUtil;
import com.bzfar.vo.FormatVo;
import com.bzfar.vo.TempleFileVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    private FileConfig fileConfig;

    @Override
    public FormatVo conversionFormat(String oldType, String sourceFilePath) {
        TempleFileVo tempFile = tempFileService.createTempFile(oldType, sourceFilePath);
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/license/license.xml");
        License aposeLic = new License();
        try {
            aposeLic.setLicense(resourceAsStream);
        } catch (Exception e) {
            log.error("【word转图片，license获取错误】 = {}",e.getMessage());
        }
        String path = tempFile.getPath();
        path = path.endsWith("/") ? path : path + "/";

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(path + tempFile.getFileName()));
        } catch (FileNotFoundException e) {
            log.error("【word转图片，临时文件获取有误】");
        }
        String httpPath = fileConfig.getHttpPath();
        AssertUtil.assertEmpty(httpPath, "网络配置路径有误，请检查配置文件");
        String storePath = fileConfig.getStorePath();
        AssertUtil.assertEmpty(storePath, "物理配置路径有误，请检查配置文件");
        httpPath = httpPath.endsWith("/") ? httpPath : httpPath + "/";
        storePath = storePath.endsWith("/") ? storePath : storePath + "/";
        TreeSet<String> imgUrl = new TreeSet<>();
        try {
            Document doc = new Document(fileInputStream);
            ImageSaveOptions iso = new ImageSaveOptions(SaveFormat.JPEG);
            iso.setPrettyFormat(true);
            iso.setUseAntiAliasing(true);
            iso.setJpegQuality(80);
            for (int i = 0; i < doc.getPageCount(); i++) {
                String imgName = tempFile.getName() + "_" + i + ".jpg";
                iso.setPageIndex(i);
                doc.save(storePath+imgName, iso);
                imgUrl.add(httpPath + imgName);
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
//        return null;
    }
}
