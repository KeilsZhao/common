package com.bzfar.service.impl;

import com.bzfar.config.FileConfig;
import com.bzfar.service.FormatConversionService;
import com.bzfar.service.PdfService;
import com.bzfar.service.TempFileService;
import com.bzfar.vo.FormatVo;
import com.bzfar.vo.TempleFileVo;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.TreeSet;

/**
 * pdf转图片，如果乱码情况需继续使用，使用PDFToImg
 */
@Service
@Slf4j
public class PdfToJpegServiceImpl implements FormatConversionService {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private TempFileService tempFileService;

    @Autowired
    private FileConfig fileConfig;


    @Override
    public FormatVo conversionFormat(String oldType, String sourceFilePath) {
        TempleFileVo tempFile = tempFileService.createTempFile(oldType, sourceFilePath);
        TreeSet<String> imgUrl = new TreeSet<>();
        try {
            PdfReader reader = new PdfReader(tempFile.getFilePath());
            int pageCnt = reader.getNumberOfPages();
            for (int i = 1; i <= pageCnt; i++) {
                Long startTs = System.currentTimeMillis();
                String newName = startTs + "-new-" + i + ".pdf";
                String newPath = tempFile.getPath() + newName;
                Document document = new Document(reader.getPageSizeWithRotation(i));
                PdfCopy copy = new PdfCopy(document, new FileOutputStream(newPath));
                document.open();
                copy.addPage(copy.getImportedPage(reader, i));
                document.close();
                String newJpg = startTs + "-new-" + i + ".jpg";
                pdfService.pdf2png(newPath, fileConfig.getStorePath() + newJpg, "jpg");
                String s = fileConfig.getHttpPath().endsWith("/") ? fileConfig.getHttpPath() : fileConfig.getHttpPath() + "/";
                imgUrl.add(s+newJpg);
            }
        } catch (Exception e) {
            log.error("【pdf转jgp错误】 = {}", e.getMessage());
        }
        // 完成之后删除临时文件
        tempFileService.deleteTempFile(tempFile.getPath());
        return FormatVo.builder().imgUrl(imgUrl).build();
    }


}
