package com.bzfar.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("案件批量下载返回")
public class CaseBatchVo {

    @JsonProperty("AYMS")
    @ApiModelProperty("案由明细")
    private String ayms;

    @JsonProperty("AJLB")
    @ApiModelProperty("案件类别")
    private String ajlb;

    @JsonProperty("CBR")
    @ApiModelProperty("承办人")
    private String cbr;

    @JsonProperty("AJMC")
    @ApiModelProperty("案件名称")
    private String ajmc;

    @JsonProperty("DSR")
    @ApiModelProperty("当事人")
    private String dsr;

    @JsonProperty("AH")
    @ApiModelProperty("案号")
    private String ah;

    @JsonProperty("FYDMMS")
    @ApiModelProperty("法院代码明细")
    private String fydmms;
}
