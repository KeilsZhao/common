package com.bzfar.config;

import com.bzfar.enums.FileOrigin;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Ethons
 * @date 2021-7-6 11:04
 */
@ApiModel("文件属性")
@Data
public class FileDefinition {

    @ApiModelProperty("文件来源")
    private FileOrigin origin;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("文件类型")
    private String type;

    @ApiModelProperty("文件的新名称")
    private String newName;

    @ApiModelProperty("网络地址")
    private String url;

    @ApiModelProperty("账号")
    private String userName;

    @ApiModelProperty("密码")
    private String passWord;
}
