package com.bzfar.vo.thrid;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@ApiModel("第三方返回开庭排期信息详情")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThirdWaitInfoVo {

    @JsonProperty("Count")
    @ApiModelProperty("总条数")
    private String count;

    @JsonProperty("ServerTime")
    @ApiModelProperty("服务器时间")
    private String ServerTime;

    @JsonProperty("EAJ_FTSY")
    @ApiModelProperty("第三方返回开庭排期信息列表详情")
    private List<ThirdWaitInfoListVo> waitInfoListVoList;
}
