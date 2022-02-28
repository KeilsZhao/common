package com.bzfar.enums;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

/**
 * @author ""
 * @Description FormatConversionEnum
 * @Date 2021/11/30 10:30
 */
@ApiModel("格式转换包路径枚举")
@Getter
public enum FormatConversionEnum {

    WORDTOPDF(1, "word转pdf（doc及docx格式）", "com.bzfar.service.impl.WordToPdfServiceImpl"),
    TIFTOJPEG(2, "tif转jpeg格式", "com.bzfar.service.impl.TifToJpegServiceImpl"),
    PDFTOJPG(3, "pdf转换为jpg", "com.bzfar.service.impl.PdfToJpegServiceImpl"),
    WRORDTOIMG(4, "word转图片", "com.bzfar.service.impl.WordToImgServiceImpl"),
    PDFTOIMG(5, "pdf转换为图片", "com.bzfar.service.impl.PdfToImgServiceImpl");


    FormatConversionEnum(Integer id, String desc, String packageUrl) {
        this.id = id;
        this.desc = desc;
        this.packageUrl = packageUrl;
    }


    /**
     * 类型ID
     */
    private Integer id;

    /**
     * 描述
     */
    private String desc;

    /**
     * 包路径
     */
    private String packageUrl;

}
