package com.bzfar.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("约见法官案件列表信息")
public class ConnectJudgeCaseVo {

    @ApiModelProperty("流水号")
    private String lsh;

    @ApiModelProperty("身份证号")
    private String sfzh;

    @ApiModelProperty("代字")
    private String dz;

    @ApiModelProperty("案号")
    private String ah;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("案由")
    private String ay;

    @ApiModelProperty("立案日期")
//    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+8")
    private String larq;

    @ApiModelProperty("结案日期")
//    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+8")
    private String jarq;

    @ApiModelProperty("承办部门")
    private String cbbm;

    @ApiModelProperty("承办人")
    private String cbr;

    @ApiModelProperty("适用程序")
    private String sycx;

    @ApiModelProperty("原告")
    private String yg;

    @ApiModelProperty("被告")
    private String bg;

}
