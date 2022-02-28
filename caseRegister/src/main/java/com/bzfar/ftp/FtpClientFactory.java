package com.bzfar.ftp;

import com.bzfar.config.FtpConfig;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * ClassName:FtpClientFactory
 * Package:com.bzfar.ftp
 * Description:
 *
 * @author:""
 * @since :2021/11/15 14:09
 */
@Component
public class FtpClientFactory implements PooledObjectFactory<FTPClient> {

    @Autowired
    private FtpConfig config;

    /**
     * 创建连接到池中
     */
    @Override
    public PooledObject<FTPClient> makeObject() {
        //创建客户端实例
        FTPClient ftpClient = new FTPClient();
        return new DefaultPooledObject<>(ftpClient);
    }

    /**
     * 销毁连接，当连接池空闲数量达到上限时，调用此方法销毁连接
     * @param pooledObject
     */
    @Override
    public void destroyObject(PooledObject<FTPClient> pooledObject)  {
        FTPClient ftpClient = pooledObject.getObject();
        try {
            ftpClient.logout();
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not disconnect from server.", e);
        }
    }

    /**
     * 链接状态检查
     * @param pooledObject
     * @return
     */
    @Override
    public boolean validateObject(PooledObject<FTPClient> pooledObject) {
        FTPClient ftpClient = pooledObject.getObject();
        try {
            return ftpClient.sendNoOp();
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 初始化连接
     * @param pooledObject
     * @throws Exception
     */
    @Override
    public void activateObject(PooledObject<FTPClient> pooledObject) throws Exception {
        FTPClient ftpClient = pooledObject.getObject();
        ftpClient.connect(config.getHost(),config.getPort());
        ftpClient.login(config.getUserName(), config.getPassword());
        ftpClient.setControlEncoding("utf-8");
        ftpClient.changeWorkingDirectory(config.getWorkDir());
        // 设置上传文件类型为二进制，否则将无法打开文件
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    }

    /**
     * 钝化连接，使链接变为可用状态
     * @param pooledObject
     * @throws Exception
     */
    @Override
    public void passivateObject(PooledObject<FTPClient> pooledObject) throws Exception {
        FTPClient ftpClient = pooledObject.getObject();
        try {
            ftpClient.changeWorkingDirectory(config.getRoot());
            ftpClient.logout();
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not disconnect from server.", e);
        }
    }

    /**
     * 用于连接池中获取pool属性
     * @return
     */
    public FtpConfig getConfig() {
        return config;
    }
}
