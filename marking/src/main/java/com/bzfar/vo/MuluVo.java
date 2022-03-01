package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("目录数据")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MuluVo {

    @ApiModelProperty("卷宗类别")
    private String jzlb;

    @ApiModelProperty("类型")
    private String pgtype;

    @ApiModelProperty("页码")
    private String yema;

    @ApiModelProperty("ossname")
    private String ossname;

    @ApiModelProperty("ossf")
    private String ossfimename;

    @ApiModelProperty("filename")
    private String filename;

    @ApiModelProperty("ftpserver")
    private String ftpserver;

}
