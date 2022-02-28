package com.bzfar.dto.ftp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * ClassName:Base64Dto
 * Package:com.bzfar.dto
 * Description:
 *
 * @author:""
 * @since :2021/11/10 15:52
 */
@ApiModel("BASE64图片上传参数")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Base64Dto {

    @ApiModelProperty("图片Base64信息")
    @NotBlank(message = "base64信息不能为空")
    private String base64;

    @ApiModelProperty("图片类型")
    @NotBlank(message = "图片后缀类型不能为空")
    private String extention;

    @ApiModelProperty("临时存储名称")
    @NotBlank(message = "临时存储名称不能为空")
    private String name;
}
