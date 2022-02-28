package com.bzfar.vo.caseSearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("案件查询信息-开庭信息")
public class ThirdCaseKtxxVo {

    @ApiModelProperty("开始时间")
    @JsonProperty("KSSJ")
    private String kssj;

    @ApiModelProperty("结束时间")
    @JsonProperty("JSSJ")
    private String jssj;

    @ApiModelProperty("开庭法庭")
    @JsonProperty("KTFT")
    private String ktft;

}
