package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 刘成
 * @date 2021-6-8
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("目录数据")
public class FileVo {

    @ApiModelProperty("类别")
    private String category;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("页码")
    private String page;

    private String ossName;

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("ftp信息")
    private String ftpServer;
}
