package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author Administrator
 */
@ApiModel("卷宗查询-当事人信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CaseDsrInfoVo {

    @ApiModelProperty("当事人类型：1 自然人 2 法人")
    private String type;

    @ApiModelProperty("当事人地位：1 原告 2 被告")
    private String dw;

    @ApiModelProperty("姓名，法人时为公司名称")
    private String name;

    @ApiModelProperty("身份证号，法人时为社会信用代码")
    private String zjh;

    @ApiModelProperty("代理人信息")
    private List<CaseAgentInfoVo> dlr;

}
