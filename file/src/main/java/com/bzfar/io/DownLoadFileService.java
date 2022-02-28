package com.bzfar.io;


import java.io.File;

/**
 * @author Ethons
 * @date 2021-7-5 12:00
 */
public interface DownLoadFileService {

    /**
     * 网络下载文件
     * @param url
     * @return
     */
    File downLoadFile(String url);

}
