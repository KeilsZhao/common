package com.bzfar.service;

import com.bzfar.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * @author 刘成
 * @date 2021-6-9
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class FileServiceTest {

    @Autowired
    private FileService fileService;


    @Test
    public void fileUpload() throws Exception{
        File file = new File("D:\\docker\\nginx\\www\\jpg\\7ae94639-4812-49dd-8568-b774b4b316be.jpg");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(),file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),inputStream);
        FileVO fileVO = fileService.fileUpload(multipartFile);
        log.info("【上传】file = {}" , fileVO);
        inputStream.close();
    }
}