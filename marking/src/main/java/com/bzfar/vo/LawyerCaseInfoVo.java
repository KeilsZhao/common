package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "卷宗查询-律师查询案件")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LawyerCaseInfoVo {

    @ApiModelProperty("是否直接进入详情界面：true 是 false 否（调用获取目录接口）")
    private boolean isDetail;

    @ApiModelProperty("案件概要信息，如果不能直接进入详情")
    private CaseProfileInfoVo caseProfileInfoVo;

}
