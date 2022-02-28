package com.bzfar.service.impl;

import com.bzfar.config.FileConfig;
import com.bzfar.service.FormatConversionService;
import com.bzfar.service.TempFileService;
import com.bzfar.vo.FormatVo;
import com.bzfar.vo.TempleFileVo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import java.io.*;

@Service
@Slf4j
public class TifToJpegServiceImpl implements FormatConversionService {

    @Autowired
    private FileConfig fileConfig;

    @Autowired
    private TempFileService tempFileService;

    @Override
    public FormatVo conversionFormat(String oldType, String sourceFilePath) {
        TempleFileVo tempFile = tempFileService.createTempFile(oldType, sourceFilePath);
        String outputPath = fileConfig.getStorePath() + tempFile.getName() + ".jpeg";
        // 读取tiff图片文件
        RenderedOp file = JAI.create("fileload", tempFile.getFilePath());
        OutputStream ops = null;
        try {
            ops = new FileOutputStream(outputPath);
            // 文件存储输出流
            JPEGEncodeParam param = new JPEGEncodeParam();
            // 指定输出格式
            ImageEncoder image = ImageCodec.createImageEncoder("JPEG", ops, param);
            // 解析输出流进行输出
            image.encode(file);
            // 关闭流
            ops.close();
        } catch (FileNotFoundException e) {
            log.error("【tif转jpeg文件无法找到】 = {}", e.getMessage());
        } catch (IOException e) {
            log.error("【tif转jpeg流异常】 = {}", e.getMessage());
        }
        FormatVo formatVo = FormatVo.builder()
                .httpUrl(outputPath)
                .build();
        // 完成之后删除临时文件
        tempFileService.deleteTempFile(tempFile.getPath());
        return formatVo;
    }
}
