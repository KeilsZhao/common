package com.bzfar.io.impl;

import com.bzfar.exception.DataException;
import com.bzfar.io.DownLoadFileService;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Ethons
 * @date 2021-7-5 15:04
 */
@Service
public class DownLoadFileServiceImpl implements DownLoadFileService {



    @Override
    public File downLoadFile(String fileUrl) {
        URL url = null;
        File file = new File("src/main/resources/targetFile.tmp");
        try{
            url = new URL(fileUrl);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            int bytesum = 0;
            int byteread;
            byte[] buffer = new byte[1024];
            while ((byteread = inputStream.read(buffer)) != -1) {
                bytesum += byteread;
                fileOutputStream.write(buffer, 0, byteread);
            }
            inputStream.close();
            fileOutputStream.close();
            return file;
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
    }
}
