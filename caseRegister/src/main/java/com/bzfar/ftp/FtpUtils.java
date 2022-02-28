package com.bzfar.ftp;

import com.bzfar.config.FtpConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * ClassName:FtpUtils
 * Package:com.bzfar.ftp
 * Description:
 *
 * @author:""
 * @since :2021/11/15 14:22
 */
@Component
@Slf4j
public class FtpUtils {

    @Autowired
    private FtpConfig ftpConfig;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private FtpPool pool;

    /**
     * Description: 向FTP服务器上传文件
     *
     * @param inputStream 上传到FTP服务器上的文件
     */
    public void upload(String filePath, String fileName, InputStream inputStream) throws Exception {
        FTPClient ftpClient = pool.getFTPClient();
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            if (!existDirectory(filePath, ftpClient)) {
                createDirectory(filePath, ftpClient);
            }
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(filePath);
            //执行文件传输
            boolean result = ftpClient.storeFile(fileName, inputStream);
            //上传失败
            if (!result) {
                log.info("上传失败");
            }
        } catch (Exception e) {
            log.error("【上传文件{}错误】 = {}", fileName, e.getMessage());
        } finally {
            //关闭资源
            inputStream.close();
            log.info("开始归还连接");
            //归还资源
            pool.returnFTPClient(ftpClient);
        }
    }

    /**
     * 检查目录是否存在
     *
     * @param path 目录
     * @return
     * @throws IOException
     */
    public boolean existDirectory(String path, FTPClient ftpClient) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        for (FTPFile ftpFile : ftpFileArr) {
            if (ftpFile.isDirectory() && ftpFile.getName().equalsIgnoreCase(path)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 创建FTP文件夹目录
     *
     * @param path 目录
     * @return
     * @throws IOException
     */
    public boolean createDirectory(String path, FTPClient ftp) throws IOException {
        //分割
        String[] paths = path.split("/");
        //创建成功标识
        boolean isMakeSucess=false;
        //遍历每一级路径
        for (String str : paths) {
            if (StringUtils.isBlank(str)) {
                continue;
            }
            //该级路径不存在就创建并切换
            if (!ftp.changeWorkingDirectory(str)) {
                isMakeSucess = ftp.makeDirectory(str);
                boolean changeWorkingDirectory = ftp.changeWorkingDirectory(str);
            } else {
                //切换路径
                boolean changeWorkingDirectory = ftp.changeWorkingDirectory(str);
            }
        }
        return isMakeSucess;
    }


}
