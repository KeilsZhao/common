package com.bzfar.vo;

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
@ApiModel("约见法官案件列表返回信息")
public class ConnectVo {

    @ApiModelProperty("案件列表信息")
    private List<ConnectJudgeCaseVo> data;

}
