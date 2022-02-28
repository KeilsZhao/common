package com.bzfar.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;

@Slf4j
public abstract class HandleFile {

    /**
     * 下一个处理文件的
     */
    private HandleFile nextHandleFile;

    /**
     * 文件路径
     */
    private String filePath;


    public HandleFile(String filePath){
        this.filePath = filePath;
    }

    /**
     * 处理文件读取
     * @param mockName
     * @return
     */
    public final InputStream handleMockData(String mockName){
        if (checkFile(mockName)) {
           return this.getMockData(mockName);
        } else {
            if (this.nextHandleFile != null) {
                return this.nextHandleFile.getMockData(mockName);
            } else {
                return null;
            }
        }
    }

    private boolean checkFile(String mockName){
        try {
            log.info("【file path = 】" + this.filePath + mockName);
            File file = new File(this.filePath + mockName);
            if (file.exists() && file.isFile() && file.length() != 0) {
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    /** 设置下一个处理请求 */
    public void setNextHandle(HandleFile nextHandleFile) {
        this.nextHandleFile = nextHandleFile;
    }


    /** 读取文件流 */
    protected abstract InputStream getMockData(String mockName);
}
