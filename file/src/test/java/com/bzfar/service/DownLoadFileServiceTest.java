package com.bzfar.service;

import com.bzfar.io.DownLoadFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Ethons
 * @date 2021-7-6 10:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DownLoadFileServiceTest {

    @Autowired
    private DownLoadFileService service;

    @Test
    public void downLoadFile() throws Exception{
        service.downLoadFile("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fblog%2F201306%2F25%2F20130625150506_fiJ2r.jpeg&refer=http%3A%2F%2Fcdn.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628130946&t=4c67a4351270b40ed5fc99a676adb7e7");
    }
}