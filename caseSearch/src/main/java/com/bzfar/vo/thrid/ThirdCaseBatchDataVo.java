package com.bzfar.vo.thrid;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("案件批量下载第三方返回数据")
public class ThirdCaseBatchDataVo {

    @ApiModelProperty("最大页码数量")
    @JsonProperty("TotalPageNum")
    private String totalPageNum;


    @ApiModelProperty("总条数")
    @JsonProperty("Count")
    private String count;

    @ApiModelProperty("服务器时间")
    @JsonProperty("ServerTime")
    private String serverTime;

    @ApiModelProperty("当前页")
    @JsonProperty("CurPageNum")
    private String curPageNum;


    @ApiModelProperty("详情列表信息")
    @JsonProperty("EAJ")
    private List<ThirdCaseBatchInfoVo> batchInfoVoList;
}
