package com.bzfar.service.impl;

import com.bzfar.service.PdfService;
import com.bzfar.utils.PdfToImageUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author ""
 * @Description PdfServiceImpl
 * @Date 2021/11/30 17:21
 */
@Service
public class PdfServiceImpl implements PdfService {

//    @Async("doSomethingExecutor")
    @Override
    public void pdf2png(String fileAddress, String filename, String type) {
        PdfToImageUtil.pdf2png(fileAddress  , filename,type);
    }

}
