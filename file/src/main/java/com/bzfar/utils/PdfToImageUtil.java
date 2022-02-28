package com.bzfar.utils;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PdfToImageUtil {



    /**
     * 转换全部的pdf
     * @param fileAddress 文件地址
     * @param filename PDF文件名
     * @param type 图片类型
     */
    public static void pdf2png(String fileAddress,String filename,String type)  {
        File file = new File(fileAddress);
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 144); // Windows native DPI
                // BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
//                image.createGraphics().setFont(new Font("SimSun",Font.CENTER_BASELINE,24));
                ImageIO.write(image, type, new File(filename));
            }
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public static void main(String args[]) {
//
//        pdf2png("D:\\upload\\new-1.pdf","D:\\upload\\new-1.jpg","jpg");
//
//    }


}
