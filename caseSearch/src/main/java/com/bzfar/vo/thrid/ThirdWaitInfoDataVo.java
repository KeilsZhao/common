package com.bzfar.vo.thrid;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("第三方返回开庭排期信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThirdWaitInfoDataVo {

    @ApiModelProperty("返回码")
    @JsonProperty("code")
    private Integer code;

    @ApiModelProperty("返回值信息")
    @JsonProperty("msg")
    private String msg;

    @ApiModelProperty("第三方返回数据")
    private ThirdWaitInfoMessageVo data;

}
