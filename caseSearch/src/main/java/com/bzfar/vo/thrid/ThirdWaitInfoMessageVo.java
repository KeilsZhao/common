package com.bzfar.vo.thrid;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("第三方返回开庭排期接口信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThirdWaitInfoMessageVo {

    @ApiModelProperty("接口数据")
    @JsonProperty("Data")
    private ThirdWaitInfoVo thirdWaitInfoVo;
}
