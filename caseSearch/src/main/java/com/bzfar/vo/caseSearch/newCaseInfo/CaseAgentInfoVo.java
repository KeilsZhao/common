package com.bzfar.vo.caseSearch.newCaseInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "案件代理人信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CaseAgentInfoVo {

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("律师事务所")
    private String company;

    @ApiModelProperty("律师资格证号")
    private String zjh;

    @ApiModelProperty("电话")
    private String phone;
}
