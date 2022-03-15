package com.bzfar.vo.caseSearch.zxNewCaseInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "律师查询执行案件")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ZxLawyerCaseInfoVo {

    @ApiModelProperty("是否直接进入详情界面：true 是 false 否")
    private boolean isDetail;

    @ApiModelProperty("执行案件概要信息，如果不能直接进入详情，取该字段值")
    private ZxCaseProfileInfoVo zxCaseProfileInfoVo;

    @ApiModelProperty("执行案件详情信息，如果直接进入详情界面，取该字段值")
    private ZxCaseInfoVo zxCaseInfoVo;

}
