package com.bzfar.dto.stand;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * ClassName:FileUploadDto
 * Package:com.dto.jianyang
 * Description:
 *
 * @author:""""
 * @since :2021/9/27 10:33
 */
@ApiModel("文件上传请求参数")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileUploadDto {

    @ApiModelProperty("base64内容")
    private String base64;

    @ApiModelProperty("文件后缀")
    private String extention;

}