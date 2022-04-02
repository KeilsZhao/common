package com.bzfar.service.impl;

import com.aspose.pdf.Document;
import com.aspose.pdf.devices.JpegDevice;
import com.aspose.pdf.devices.Resolution;
import com.bzfar.service.FormatConversionService;
import com.bzfar.service.TempFileService;
import com.bzfar.utils.AsposeLicenseUtil;
import com.bzfar.utils.PathUtil;
import com.bzfar.vo.FormatVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.TreeSet;

/**
 * @author ""
 * @Description PdfToImgServiceImpl
 * @Date 2022/1/17 10:36
 */
@Service
@Slf4j
public class PdfToImgServiceImpl implements FormatConversionService {

    @Autowired
    private TempFileService tempFileService;

    @Autowired
    private PathUtil pathUtil;


    @Override
    public synchronized FormatVo conversionFormat(String oldType, String sourceFilePath) {
        if (!AsposeLicenseUtil.pdfLicense()) {
            return null;
        }
        InputStream fileInputStream = tempFileService.getFileStream(sourceFilePath);
        TreeSet<String> imgUrl = new TreeSet<>();
        try {
            long old = System.currentTimeMillis();
            log.info("convert pdf2jpg begin");
            Document pdfDocument = new Document(fileInputStream);
            // 图片宽度：800 图片高度：100 分辨率 960
            // Quality [0-100] 最大100
            // 例： new JpegDevice(800, 1000, resolution, 90);
            Resolution resolution = new Resolution(960);
            JpegDevice jpegDevice = new JpegDevice(resolution);
            Long startTs = System.currentTimeMillis();
            for (int index = 1; index <= pdfDocument.getPages().size(); index++) {
                // 输出路径
                String newName = startTs + "-new-" + index + ".jpg";
                File file = new File(pathUtil.concatStore(newName));
                FileOutputStream fileOs = new FileOutputStream(file);
                jpegDevice.process(pdfDocument.getPages().get_Item(index), fileOs);
                fileOs.close();
                imgUrl.add(pathUtil.concatHttp(newName));
            }
        } catch (Exception e) {
            log.error("convert pdf2jpg error:" + e);
        } finally {
            try {
                if (null != fileInputStream) {
                    fileInputStream.close();
                }
            } catch (Exception e) {
                log.error("【pdf转图片流关闭异常】 = {}", e.getMessage());
            }
        }
        return FormatVo.builder().imgUrl(imgUrl).build();
    }
}
