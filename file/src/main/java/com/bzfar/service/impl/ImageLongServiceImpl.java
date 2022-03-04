package com.bzfar.service.impl;

import com.bzfar.exception.DataException;
import com.bzfar.service.ImageLongService;
import com.bzfar.service.TempFileService;
import com.bzfar.utils.PathUtil;
import com.bzfar.vo.FormatVo;
import com.bzfar.vo.TempleFileVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ImageLongServiceImpl implements ImageLongService {

    @Autowired
    private PathUtil pathUtil;

    @Autowired
    private TempFileService tempFileService;

    @Override
    public FormatVo splicingImage(List<TempleFileVo> templeFileVos, Boolean isHorizontal) {
        try {
            List<BufferedImage> collect = templeFileVos.stream().map(item -> {
                BufferedImage bimg = null;
                InputStream inStream = tempFileService.getFileStream(item.getFilePath());
                try {
                    bimg = ImageIO.read(inStream);
                } catch (IOException e) {
                    log.error("{}->文件读取错误={}", item.getFilePath(), e.getMessage());
                }
                return bimg;
            }).collect(Collectors.toList());
            BufferedImage mergeImage = mergeImage(isHorizontal, collect);
            String newFileName = System.currentTimeMillis() + "_" + new Random().nextInt(100000) + ".png";
            ImageIO.write(mergeImage, "png", new File(pathUtil.concatStore(newFileName)));
            tempFileService.listDeleteTempFile(templeFileVos);
            return FormatVo.builder().httpUrl(pathUtil.concatHttp(newFileName)).build();
        } catch (Exception e) {
            throw new DataException("图片合成错误=" + e.getMessage());
        }
    }

    /**
     * 合并任数量的图片成一张图片
     *
     * @param isHorizontal true代表水平合并，fasle代表垂直合并
     * @param imgs         待合并的图片数组
     * @return
     * @throws IOException
     */
    private BufferedImage mergeImage(boolean isHorizontal, List<BufferedImage> imgs) throws IOException {
        // 生成新图片
        BufferedImage destImage = null;
        // 计算新图片的长和高
        int allw = 0, allh = 0, allwMax = 0, allhMax = 0;
        // 获取总长、总宽、最长、最宽
        for (int i = 0; i < imgs.size(); i++) {
            BufferedImage img = imgs.get(i);
            allw += img.getWidth();

            if (imgs.size() != i + 1) {
                allh += img.getHeight() + 5;
            } else {
                allh += img.getHeight();
            }


            if (img.getWidth() > allwMax) {
                allwMax = img.getWidth();
            }
            if (img.getHeight() > allhMax) {
                allhMax = img.getHeight();
            }
        }
        // 创建新图片
        if (isHorizontal) {
            destImage = new BufferedImage(allw, allhMax, BufferedImage.TYPE_INT_RGB);
        } else {
            destImage = new BufferedImage(allwMax, allh, BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D g2 = (Graphics2D) destImage.getGraphics();
        g2.setBackground(Color.LIGHT_GRAY);
        g2.clearRect(0, 0, allw, allh);
        g2.setPaint(Color.RED);

        // 合并所有子图片到新图片
        int wx = 0, wy = 0;
        for (int i = 0; i < imgs.size(); i++) {
            BufferedImage img = imgs.get(i);
            int w1 = img.getWidth();
            int h1 = img.getHeight();
            // 从图片中读取RGB
            int[] ImageArrayOne = new int[w1 * h1];
            // 逐行扫描图像中各个像素的RGB到数组中
            ImageArrayOne = img.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1);
            if (isHorizontal) {
                // 水平方向合并
                // 设置上半部分或左半部分的RGB
                destImage.setRGB(wx, 0, w1, h1, ImageArrayOne, 0, w1);
            } else {
                // 垂直方向合并
                // 设置上半部分或左半部分的RGB
                destImage.setRGB(0, wy, w1, h1, ImageArrayOne, 0, w1);
            }
            wx += w1;
            wy += h1 + 5;
        }


        return destImage;
    }
}
