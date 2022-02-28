package com.bzfar.service;

import org.springframework.scheduling.annotation.Async;

/**
 * @author ""
 * @Description TempFileService
 * @Date 2021/11/29 16:36
 */
public interface PdfService {

//    @Async("doSomethingExecutor")
    void pdf2png(String fileAddress,String filename,String type);
}
