package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author ""
 * @Description TempleFileVo
 * @Date 2021/11/30 17:52
 */
@ApiModel("临时文件返回信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TempleFileVo {

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件名称，不带后缀")
    private String name;

    @ApiModelProperty("文件路径（全）")
    private String filePath;

    @ApiModelProperty("存储的位置")
    private String path;

}
