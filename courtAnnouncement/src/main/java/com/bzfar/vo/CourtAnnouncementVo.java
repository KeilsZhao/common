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
@ApiModel("开庭排期")
public class CourtAnnouncementVo {

    @ApiModelProperty("代字")
    private String dz;

    @ApiModelProperty("案由明细")
    private String ay;

    @ApiModelProperty("案号")
    private String ah;

    @ApiModelProperty("开庭时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date courtTime;

    @ApiModelProperty("开庭地点")
    private String courtAddress;

    @ApiModelProperty("承办人")
    private String cbr;

    @ApiModelProperty("原告")
    private String yg;

    @ApiModelProperty("被告")
    private String bg;

    @ApiModelProperty("开庭状态")
    private String status;
}
