package com.bzfar.service.impl;

import com.aspose.pdf.Document;
import com.aspose.pdf.License;
import com.aspose.pdf.devices.JpegDevice;
import com.aspose.pdf.devices.Resolution;
import com.bzfar.config.FileConfig;
import com.bzfar.service.FormatConversionService;
import com.bzfar.service.TempFileService;
import com.bzfar.util.AssertUtil;
import com.bzfar.vo.FormatVo;
import com.bzfar.vo.TempleFileVo;
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
    private FileConfig fileConfig;

    @Override
    public FormatVo conversionFormat(String oldType, String sourceFilePath) {
        TempleFileVo tempFile = tempFileService.createTempFile(oldType, sourceFilePath);
        if (!getpdfLicense()) { // 验证License 若不验证则转化出的会有水印产生
            return null;
        }
        String path = tempFile.getPath();
        path = path.endsWith("/") ? path : path + "/";

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(path + tempFile.getFileName()));
        } catch (FileNotFoundException e) {
            log.error("【pdf转图片，临时文件获取有误】");
        }
        String httpPath = fileConfig.getHttpPath();
        AssertUtil.assertEmpty(httpPath, "网络配置路径有误，请检查配置文件");
        String storePath = fileConfig.getStorePath();
        AssertUtil.assertEmpty(storePath, "物理配置路径有误，请检查配置文件");
        httpPath = httpPath.endsWith("/") ? httpPath : httpPath + "/";
        storePath = storePath.endsWith("/") ? storePath : storePath + "/";
        TreeSet<String> imgUrl = new TreeSet<>();
        try {
            long old = System.currentTimeMillis();
            log.info("convert pdf2jpg begin");
            Document pdfDocument = new Document(fileInputStream);
            //图片宽度：800
            //图片高度：100
            // 分辨率 960
            //Quality [0-100] 最大100
            //例： new JpegDevice(800, 1000, resolution, 90);
            Resolution resolution = new Resolution(960);
            JpegDevice jpegDevice = new JpegDevice(resolution);
            for (int index = 1; index <= pdfDocument.getPages().size(); index++) {
                // 输出路径
                Long startTs = System.currentTimeMillis();
                String newName = startTs + "-new-" + index + ".jpg";
                File file = new File(storePath + newName);
                FileOutputStream fileOs = new FileOutputStream(file);
                jpegDevice.process(pdfDocument.getPages().get_Item(index), fileOs);
                fileOs.close();
                imgUrl.add(httpPath + newName);
            }
        } catch (Exception e) {
            log.error("convert pdf2jpg error:" + e);
        } finally {
            tempFileService.deleteTempFile(tempFile.getFilePath());
        }
        return FormatVo.builder().imgUrl(imgUrl).build();
    }

    public static boolean getpdfLicense() {
        boolean result2 = false;
        try {
            String license2 = "<License>\n"
                    + "  <Data>\n"
                    + "    <Products>\n"
                    + "      <Product>Aspose.Total for Java</Product>\n"
                    + "      <Product>Aspose.Words for Java</Product>\n"
                    + "    </Products>\n"
                    + "    <EditionType>Enterprise</EditionType>\n"
                    + "    <SubscriptionExpiry>20991231</SubscriptionExpiry>\n"
                    + "    <LicenseExpiry>20991231</LicenseExpiry>\n"
                    + "    <SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n"
                    + "  </Data>\n"
                    + "  <Signature>111</Signature>\n"
                    + "</License>";
            InputStream is2 = new ByteArrayInputStream(
                    license2.getBytes("UTF-8"));
            com.aspose.pdf.License asposeLic2 = new com.aspose.pdf.License();
            asposeLic2.setLicense(is2);
            result2 = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result2;
    }
}
