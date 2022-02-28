package com.bzfar.service;

import com.bzfar.dto.AddWatermarkDTO;
import com.bzfar.dto.QrDto;
import com.bzfar.vo.FileVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {

    /**
     * 文件上传
     * @param multipartFile 二进制流
     * @return
     * @see FileVO
     */
    FileVO fileUpload(MultipartFile multipartFile);

    /**
     * 内容形成二维码
     * @param context 内容详情
     * @return base64二维码
     */
    String generatBase64Encode(String context);


    /**
     * 内容生成自定义大小二维码
     * @param qrDto 内容详情
     * @return base64二维码
     */
    String generatQr(QrDto qrDto);

    /**
     * 图片叠加水印
     * @param dto 叠加水添加参数
     * @return
     */
    String base64ByImageAddWatermark(@Validated AddWatermarkDTO dto);


    /**
     * 文件转化为pdf
     * @param fileSpace 文件物理路径或者网络路径
     * @param saveSpace 转化后pdf的保存路径
     * @param newName pdf的新名称
     */
    void transformToPdf(String fileSpace , String saveSpace , String newName);
}
