package com.bzfar.service;

import com.bzfar.vo.TempleFileVo;
import org.springframework.scheduling.annotation.Async;

import java.io.InputStream;
import java.util.List;

/**
 * @author ""
 * @Description TempFileService
 * @Date 2021/11/29 16:36
 */
public interface TempFileService {

    /**
     * 创建需要转换的临时文件（传入文件类型及文件流）
     *
     * @param fileType 文件类型
     * @param sourceFilePath 文件路径
     * @return
     */
    TempleFileVo createTempFile(String fileType,String sourceFilePath);

    /**
     * 获取文件流
     *
     * @param path 文件路径
     * @return 数据流
     */
    InputStream getFileStream(String path);


    /**
     * 创建需要转换的临时文件（传入文件类型及文件流）
     *
     * @param fileType 文件类型
     * @param inputStream 文件流
     * @return
     */
    TempleFileVo createTempFile(String fileType,InputStream inputStream);

    /**
     * 批量创建本地文件
     *
     * @param filePath 文件路径
     * @return 文件临时存储信息
     */
    List<TempleFileVo> listTempFile(List<String> filePath);

    /**
     * 批量删除临时文件
     *
     * @param fileVos
     */
    void listDeleteTempFile(List<TempleFileVo> fileVos);


    /**
     * 删除临时文件夹中的指定文件
     *
     * @param fileName 文件名称
     */
    @Async("deleteFile")
    void deleteTempFile(String fileName);
}
