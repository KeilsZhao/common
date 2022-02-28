package com.bzfar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * @author ""
 * @Description LogoInfoDto
 * @Date 2021/10/20 17:11
 */
@ApiModel("生成logo需要参数")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LogoInfoDto {

    @ApiModelProperty("二维码图片信息")
    BufferedImage source;

    @ApiModelProperty("logo流")
    InputStream inputStream;

    @ApiModelProperty("是否需要压缩")
    boolean needCompress;

    @ApiModelProperty("二维码图片宽")
    Integer qrWidth;

    @ApiModelProperty("二维码图片长")
    Integer qrHeight;

    @ApiModelProperty("logo宽")
    Integer logoWidth;

    @ApiModelProperty("logo长")
    Integer logoHeight;

}
