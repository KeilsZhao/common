package com.bzfar.utils;

import com.bzfar.config.FileConfig;
import com.bzfar.exception.DataException;
import com.bzfar.util.AssertUtil;
import com.bzfar.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author Ethons
 * @date 2022-3-23 15:13
 */
@Component
public class Base64ToImg {

    @Autowired
    private FileConfig fileConfig;

    public FileVO GenerateImage(String base64Str , String fileName){
        AssertUtil.assertEmpty(base64Str , "base64数据不能为空");
        AssertUtil.assertEmpty(fileName , "图片名称不能为空");

        BASE64Decoder decoder = new BASE64Decoder();

        // Base64解码,对字节数组字符串进行Base64解码并生成图片
        base64Str = base64Str.replaceAll(" ", "+");
        byte[] b = null;
        try{
           b = decoder.decodeBuffer(base64Str.replace("data:image/jpeg;base64,", ""));
        }catch (Exception e){
            throw new DataException("base64转字符集失败" + e.getMessage());
        }
        for (int i = 0; i < b.length; ++i) {
            // 调整异常数据
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        File file = new File(fileConfig.getStorePath());
        if (!file.isDirectory() || !file.exists()){
            file.mkdir();
        }
        try{
            OutputStream out = new FileOutputStream(fileConfig.getStorePath() + fileName);
            out.write(b);
            out.flush();
            out.close();
        }catch (Exception e){
            throw new DataException("输出流失败" + e.getMessage());
        }
        FileVO build = FileVO.builder()
                .httpUrl(fileConfig.getHttpPath() + fileName)
                .name(fileName)
                .build();
        return build;
    }
}
