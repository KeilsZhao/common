package com.bzfar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.imageio.stream.ImageInputStream;
import javax.validation.constraints.NotBlank;
import java.awt.image.BufferedImage;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加水印传递参数")
public class AddWatermarkDTO {

    @ApiModelProperty("背景图片流")
    @NotBlank(message = "背景图片流不能为空")
    private ImageInputStream backStream;

    @ApiModelProperty("水印图片流")
    @NotBlank(message = "水印图片流不能为空")
    private BufferedImage watermarkStream;

    @ApiModelProperty("水印的x坐标")
    private Integer x;

    @ApiModelProperty("水印的y坐标")
    private Integer y;

    @ApiModelProperty("旋转角度")
    private Integer degree;

    @ApiModelProperty("缩放比例，默认为1")
    private Integer scale;
}
