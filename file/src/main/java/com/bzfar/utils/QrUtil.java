package com.bzfar.utils;

import com.bzfar.dto.LogoInfoDto;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

/**
 * @author ""
 * @Description QrUtil
 * @Date 2021/10/20 16:49
 */
public class QrUtil {

    /**
     * 插入logo
     *
     * @param logoInfoDto 生成logo所需信息
     * @throws Exception
     */
    private static void insertImage(LogoInfoDto logoInfoDto) throws Exception {
        Image src = ImageIO.read(logoInfoDto.getInputStream());
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        // 压缩LOGO
        if (logoInfoDto.isNeedCompress()) {
            if (width > logoInfoDto.getLogoWidth()) {
                width = logoInfoDto.getLogoWidth();
            }
            if (height > logoInfoDto.getQrHeight()) {
                height = logoInfoDto.getLogoHeight();
            }
            Image image = src.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            // 绘制缩小后的图
            g.drawImage(image, 0, 0, null);
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = logoInfoDto.getSource().createGraphics();
        int x = (logoInfoDto.getQrWidth() - width) / 2;
        int y = (logoInfoDto.getQrHeight() - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

}
