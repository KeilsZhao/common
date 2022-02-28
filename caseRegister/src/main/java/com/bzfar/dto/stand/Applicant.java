package com.bzfar.dto.stand;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * ClassName:Applicant
 * Package:com.dto.jianyang
 * Description:
 *
 * @author:""
 * @since :2021/9/27 10:52
 */
@ApiModel("简阳立案申请人对象")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Applicant {

    @ApiModelProperty("申请人名称")
    private String applicantName;

    @ApiModelProperty("申请人角色：案件当事人(AJDSR)职业律师(ZYLS)法律服务工作者(FLFWGZZ)其他代理人(QTDLR)")
    @JsonIgnore
    private String applicantAuthCode;

    @ApiModelProperty("证件类型：09法标KIND=00015 15法标KIND=000008")
    @JsonIgnore
    private String identType;

    @ApiModelProperty("证件号码")
    private String identNo;

    @ApiModelProperty("手机号码")
    private String phone;

}
