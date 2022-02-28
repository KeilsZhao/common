package com.bzfar.util.mock;

import com.bzfar.exception.DataException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * mock数据读取解析的工具类
 * @author ethons
 */
@Slf4j
public class MockJarPathUtils extends HandleFile{


    private final static String filePath = System.getProperty("user.dir") + "/";


    public MockJarPathUtils(){
        super(filePath);
        setNextHandle(new MockFileUtils());
    }


    /**
     * 获取resources下的mock文件夹下的mock数据
     * @param mockFileName 数据文件名称
     * @return
     */
    @Override
    protected InputStream getMockData(String mockFileName){
        try{
            File file = new File(filePath,mockFileName);
            InputStream stream = new FileInputStream(file);
            return stream;
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
    }
}
