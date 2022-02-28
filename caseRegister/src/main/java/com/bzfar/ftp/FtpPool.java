package com.bzfar.ftp;

import com.bzfar.config.FtpConfig;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName:FtpPool
 * Package:com.bzfar.ftp
 * Description:
 *
 * @author:""
 * @since :2021/11/15 14:15
 */
@Component
public class FtpPool {

    FtpClientFactory factory;

    private final GenericObjectPool<FTPClient> internalPool;

    /**
     * 初始化连接池
     *
     * @param factory
     */
    public FtpPool(@Autowired FtpClientFactory factory) {
        this.factory = factory;
        FtpConfig config = factory.getConfig();
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(config.getMaxTotal());
        poolConfig.setMinIdle(config.getMinIdle());
        poolConfig.setMaxIdle(config.getMaxIdle());
        poolConfig.setMaxWaitMillis(config.getMaxWaitMillis());
        this.internalPool = new GenericObjectPool<FTPClient>(factory, poolConfig);
    }

    /**
     * 从连接池中取连接
     *
     * @return
     */
    public FTPClient getFTPClient() {
        try {
            return internalPool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将链接归还到连接池
     *
     * @param ftpClient
     */
    public void returnFTPClient(FTPClient ftpClient) {
        try {
            internalPool.returnObject(ftpClient);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 销毁池子
     */
    public void destroy() {
        try {
            internalPool.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
