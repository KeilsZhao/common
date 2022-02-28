package com.bzfar.service.ftp;

import com.bzfar.dto.ftp.Base64Dto;

import java.util.Map;

/**
 * ClassName:FtpService
 * Package:com.bzfar.service.common
 * Description:
 *
 * @author:""
 * @since :2021/11/12 16:24
 */
public interface FtpService {

    /**
     * 上传文件至Ftp服务器
     * @param base64Dto
     * @return
     */
//    @Async("uploadExecutor")
    Map<String,String> uploadFile(Base64Dto base64Dto);
}
