package com.bzfar.service;

import com.bzfar.dto.FileBackDto;
import com.bzfar.dto.FileBackSerialDto;

/**
 * @author Fimipeler
 * @Description FileBachService，用于材料补交
 * @Date 2021/6/9 17:50
 */
public interface FileBackService {

    /**
     * 材料补交时获取流水码
     *
     * @param fileBackDto 流水码请求参数
     * @return
     */
    String getSerialNumber(FileBackSerialDto fileBackDto);


    /**
     * 文件补交提交文件
     *
     * @param fileBackDto 提交文件信息
     * @return
     */
    String putFileInfo(FileBackDto fileBackDto);

}
