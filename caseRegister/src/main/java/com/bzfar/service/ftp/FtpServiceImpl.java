package com.bzfar.service.ftp;

import com.bzfar.config.FtpConfig;
import com.bzfar.dto.ftp.Base64Dto;
import com.bzfar.exception.DataException;
import com.bzfar.ftp.FtpUtils;
import com.bzfar.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * ClassName:FtpServiceImpl
 * Package:com.bzfar.service.common.impl
 * Description:
 *
 * @author:""
 * @since :2021/11/12 16:25
 */
@Service
@Slf4j
public class FtpServiceImpl implements FtpService {

    @Autowired
    private FtpUtils ftpUtil;

    @Autowired
    private FtpConfig ftpConfig;

    @Override
    public Map<String, String> uploadFile(Base64Dto base64Dto) {

        String base64 = base64Dto.getBase64();
        String name = base64Dto.getName();
        String extention = base64Dto.getExtention();
        String num = System.currentTimeMillis() + "_" + new Random().nextInt(1000000);
        String pName = num + "." + extention;


        String ftpFilePath = DateUtils.getDate("yyyy") + "/" + DateUtils.getDate("MM") + "/" + DateUtils.getDate("dd") + "/";
        String fileName = pName;
        log.info(ftpConfig.getWorkDir() + "/" + ftpFilePath + fileName);
        long length = 0;
        try {
            byte[] bytes = new BASE64Decoder().decodeBuffer(base64);
            //转化为输入流
            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            length = is.available();
            ftpUtil.upload(ftpFilePath, fileName, is);
        } catch (Exception e) {
            throw new DataException("文件上传错误");
        }

        Map<String, String> result = new HashMap<String, String>();
        result.put("WJMC", name);
        result.put("WJLJ", ftpConfig.getWorkDir() + "/" + ftpFilePath + fileName);
        result.put("WJDX", length / 1024 + "");
        result.put("WJGS", extention);
        result.put("FTPNAME", ftpConfig.getWorkDir());
        result.put("CLID", num);
        result.put("HTTP",ftpConfig.getHttp()+ftpConfig.getWorkDir() + "/" + ftpFilePath + fileName);

        return result;
    }
}
