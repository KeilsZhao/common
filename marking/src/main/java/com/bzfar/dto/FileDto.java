package com.bzfar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("卷宗文件")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDto {

    @ApiModelProperty(value = "案号")
    private String ah;

    @ApiModelProperty(value = "用户id")
    private String userid;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "FILENAME")
    private String filename;

    @ApiModelProperty(value = "FTPSERVER")
    private String ftpserver;

    @ApiModelProperty(value = "CCFS")
    private String ccfs;
}
