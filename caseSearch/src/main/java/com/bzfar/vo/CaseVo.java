package com.bzfar.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel("单条案件信息")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaseVo {

    @ApiModelProperty("案件类型")
    private String type;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("案号")
    private String ah;

    @ApiModelProperty("案由")
    private String ay;

    @ApiModelProperty("立案日期")
    private String larq = "-";

    @ApiModelProperty("结案日期")
    private String jarq = "-";

    @ApiModelProperty("承办部门")
    private String cbbm;

    @ApiModelProperty("承办人")
    private String cbr;

    @ApiModelProperty("使用程序")
    private String sycx = "-";

    @ApiModelProperty("原告")
    private String yg;

    @ApiModelProperty("被告")
    private String bg;

    @ApiModelProperty("案号代码")
    private String ahdm;

    @ApiModelProperty("结案方式")
    private String jafs;

    @ApiModelProperty("原审案号")
    private String ysah;

    @ApiModelProperty("适用程序")
    private String sxsj;

    @ApiModelProperty("立案人")
    private String lar;

    @ApiModelProperty("ajly")
    private String ajly;
}
