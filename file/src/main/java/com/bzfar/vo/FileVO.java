package com.bzfar.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileVO {

    @ApiModelProperty("base64二维码")
    private String code;

    @ApiModelProperty("静态地址路径,包含名称")
    private String url;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("网络路径，不包含名称")
    private String httpUrl;
}
