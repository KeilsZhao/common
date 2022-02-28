package com.bzfar.service.impl;

import com.bzfar.exception.DataException;
import com.bzfar.service.TempFileService;
import com.bzfar.vo.TempleFileVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author ""
 * @Description TempFileServiceImpl
 * @Date 2021/11/29 16:57
 */
@Service
@Slf4j
public class TempFileServiceImpl implements TempFileService {


    @Override
    public TempleFileVo createTempFile(String fileType, String sourceFilePath) {
        InputStream inputStream = null;
        if (sourceFilePath.startsWith("http")) {
            try {
                URL url = new URL(sourceFilePath);
                inputStream = url.openStream();
            } catch (MalformedURLException e) {
                log.error("【网络路径错误】 = {}", e.getMessage());
            } catch (IOException e) {
                log.error("【文件错误】 = {}", e.getMessage());
            }
        } else {
            File sourceFile = new File(sourceFilePath);
            try {
                inputStream = new FileInputStream(sourceFile);
            } catch (FileNotFoundException e) {
                log.error("【本地文件错误】 = {}", e.getMessage());
            }
        }
        return createTempFile(fileType, inputStream);
    }

    @Override
    public TempleFileVo createTempFile(String fileType, InputStream inputStream) {
        String newFileName = System.currentTimeMillis() + "_" + new Random().nextInt(100000);
        String filePath = System.getProperty("user.dir") + "/temp/" + newFileName;
        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();
        }
        OutputStream outSteam = null;
        String names = "";
        try {
            String filetype = fileType.startsWith(".") ? fileType : "." + fileType;
            names = newFileName + filetype;
            File tempFile = new File(filePath + "/" + names);
            if (!tempFile.exists() || !tempFile.isFile()) {
                tempFile.createNewFile();
            }
            outSteam = new FileOutputStream(tempFile);
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }

        } catch (Exception e) {
            throw new DataException("创建临时文件错误");
        } finally {
            try {
                outSteam.close();
                inputStream.close();
            } catch (IOException e) {
                log.error("关闭流异常");
            }
        }
        TempleFileVo build = TempleFileVo.builder()
                .fileName(names)
                .name(newFileName)
                .filePath(filePath + "/" + names)
                .path(filePath + "/").build();
        return build;
    }

    @Override
    public void deleteTempFile(String filePath) {
        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] files = dir.listFiles();
        for (File file : files) {
            file.delete();
            log.info("【删除文件{}成功】", file.getName());
        }
        if (dir.delete()) {
            log.info("【文件夹{}删除成功】", dir.getName());
        }
    }
}
