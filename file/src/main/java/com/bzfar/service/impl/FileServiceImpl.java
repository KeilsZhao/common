package com.bzfar.service.impl;

import com.bzfar.config.FileConfig;
import com.bzfar.dto.AddWatermarkDTO;
import com.bzfar.dto.QrDto;
import com.bzfar.enums.ResponseCode;
import com.bzfar.exception.DataException;
import com.bzfar.io.DownLoadFileService;
import com.bzfar.service.FileService;
import com.bzfar.util.AssertUtil;
import com.bzfar.vo.FileVO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.axis.encoding.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileConfig fileConfig;

    @Autowired
    private DownLoadFileService downLoadFileService;

    @Override
    public FileVO fileUpload(MultipartFile multipartFile) {
        try{
            /** 文件名 */
            String name = multipartFile.getOriginalFilename();
            /** 文件类型  png/pdf.... */
            String fileType = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
            /** 新名称 */
            String newName = UUID.randomUUID().toString().concat(".").concat(fileType);
            AssertUtil.assertEmpty(fileConfig.getHttpPath() , "配置文件中的file.http-path不能为空");
            AssertUtil.assertEmpty(fileConfig.getStorePath() , "配置文件中的file.store-path不能为空");
            /** 根据文件种类来分子路径 */
            String newPath = fileConfig.getStorePath().concat(fileType);
            File path = new File(newPath);
            if(!path.exists()){
                path.mkdirs();
            }
            File newFile = new File(newPath.concat("/").concat(newName));
            multipartFile.transferTo(newFile);
            String httpUrl = fileConfig.getHttpPath();
            String allHttpUrl = httpUrl.endsWith("/") ? httpUrl.concat(fileType).concat("/").concat(newName) : httpUrl.concat("/").concat(fileType).concat("/").concat(newName);
            FileVO build = FileVO.builder()
                    .code(generatBase64Encode(allHttpUrl))
                    .url(allHttpUrl)
                    .httpUrl(httpUrl)
                    .name(newName)
                    .build();
            return build;
        }catch (Exception e){
            throw new DataException(ResponseCode.FILE_SAVE_ERROR);
        }
    }

    @Override
    public String generatBase64Encode(String context) {
        try{
            int width = 200; // 图像宽度
            int height = 200; // 图像高度
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            HashMap<EncodeHintType, Comparable> hints = new HashMap<>();
            // 指定字符编码为“utf-8”
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); // 指定二维码的纠错等级为中级
            hints.put(EncodeHintType.MARGIN, 2); // 设置图片的边距
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(context, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ImageIO.write(bufferedImage, "png", os);
            String resultImage = new String("data:image/png;base64," + Base64.encode(os.toByteArray()));
            return resultImage;
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
    }

    @Override
    public String generatQr(QrDto qrDto) {
        try{
            int width = qrDto.getWidth(); // 图像宽度
            int height = qrDto.getHeight(); // 图像高度
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            HashMap<EncodeHintType, Comparable> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 指定字符编码为“utf-8”
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); // 指定二维码的纠错等级为中级
            if (null == qrDto.getIsCancle() || !qrDto.getIsCancle().equals(1)) {
                hints.put(EncodeHintType.MARGIN, 2); // 设置图片的边距
            } else {
                hints.put(EncodeHintType.MARGIN, 0); // 设置图片的边距
            }
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(qrDto.getContent(), BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ImageIO.write(bufferedImage, "png", os);
            String resultImage = new String("data:image/png;base64," + Base64.encode(os.toByteArray()));
            return resultImage;
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
    }


    @Override
    public String base64ByImageAddWatermark(AddWatermarkDTO dto) {
        try{
            Image image = ImageIO.read(dto.getBackStream());
            /** 背景图片的宽 */
            int width = image.getWidth(null);
            /** 背景图片的高 */
            int height = image.getHeight(null);
            int x = dto.getX() > width ? width : dto.getX();
            int y = dto.getY() > height ? height : dto.getY();
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            /** 创建绘图工具对象 */
            Graphics2D graphics2D = bufferedImage.createGraphics();
            /** 其中的0代表和原图位置一样 */
            graphics2D.drawImage(image, 0, 0, width, height, null);
            graphics2D.rotate(Math.toRadians(dto.getDegree()),(double) bufferedImage.getWidth() / 2, (double) bufferedImage.getHeight() / 2);
//            graphics2D.drawString(words.get(i), x, y);
//            graphics2D.drawRenderableImage();
            graphics2D.dispose();
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
        return null;
    }

    @Override
    public void transformToPdf(String fileSpace, String saveSpace , String newName) {
        if(fileSpace.contains("http://") || fileSpace.contains("https://")){
            downLoadFileService.downLoadFile(fileSpace);
        }
    }
}
