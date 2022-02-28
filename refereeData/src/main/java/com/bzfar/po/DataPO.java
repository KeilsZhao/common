package com.bzfar.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@ApiModel("裁判文书数据")
@Data
public class DataPO{


    @ApiModelProperty("案件名称")
    @JsonProperty("s1")
    private String title;

    @ApiModelProperty("法院名称")
    @JsonProperty("s2")
    private String countName;

    @ApiModelProperty("文书ID")
    @JsonProperty("s5")
    private String bookId;

    @ApiModelProperty("案号")
    @JsonProperty("s7")
    private String ah;

    @ApiModelProperty("案件类型")
    @JsonProperty("s8")
    private String caseType;

    @ApiModelProperty("审判程序")
    @JsonProperty("s9")
    private String trialProgram;

    @ApiModelProperty("上传日期")
    @JsonProperty("s31")
    private String uploadTime;

    @ApiModelProperty("裁判日期")
    @JsonProperty("s41")
    private String refereeTime;

    @ApiModelProperty("诉讼记录段原文")
    @JsonProperty("s23")
    private String litigationData;

    @ApiModelProperty("案件基本情况段原文")
    @JsonProperty("s25")
    private String caseData;

    @ApiModelProperty("案由信息")
    @JsonProperty("wenshuAy")
    private List<AyPO> wenshuAy;

    @ApiModelProperty("裁判要旨段原文")
    @JsonProperty("s26")
    private String refereeData;

    @ApiModelProperty("裁判结果段原文")
    @JsonProperty("s27")
    private String refereeResultData;

    @ApiModelProperty("审判人")
    @JsonProperty("s28")
    private String spr;

    @ApiModelProperty("内容")
    @JsonProperty("qwContent")
    private String content;
}
