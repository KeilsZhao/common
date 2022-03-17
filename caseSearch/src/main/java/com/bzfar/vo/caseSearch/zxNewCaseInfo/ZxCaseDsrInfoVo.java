package com.bzfar.vo.caseSearch.zxNewCaseInfo;

import com.bzfar.vo.caseSearch.newCaseInfo.CaseAgentInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author Administrator
 */
@ApiModel("执行当事人信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ZxCaseDsrInfoVo {

    @ApiModelProperty("当事人类型：1 自然人 2 法人")
    private String type;

    @ApiModelProperty("当事人地位：1 申请人 2 被执行人")
    private String dw;

    @ApiModelProperty("姓名，法人时为公司名称")
    private String name;

    @ApiModelProperty("身份证号，法人时为社会信用代码")
    private String zjh;
}
