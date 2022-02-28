package com.bzfar.util.mock;

import com.bzfar.exception.DataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.InputStream;

/**
 * mock数据读取解析的工具类,jar同级目录下文件
 *
 * @author ethons
 */
@Slf4j
public class MockFileUtils extends HandleFile{

    private final static String filePath = "classpath*:mock/";


    public MockFileUtils(){
        super(filePath);
    }


    /**
     * 获取resources下的mock文件夹下的mock数据
     * @param mockFileName 数据文件名称
     * @return
     */
    @Override
    protected InputStream getMockData(String mockFileName){
        try{
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources  = resolver.getResources(filePath + mockFileName);
            InputStream stream =resources[0].getInputStream();
            return stream;
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
    }
}
