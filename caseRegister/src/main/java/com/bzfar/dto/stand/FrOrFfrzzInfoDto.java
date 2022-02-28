package com.bzfar.dto.stand;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * ClassName:FrOrFfrzzInfoDto
 * Package:com.dto.jianyang
 * Description:
 *
 * @author:""
 * @since :2021/9/27 20:51
 */
@ApiModel("法人或非法人组织信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FrOrFfrzzInfoDto {

    @ApiModelProperty("当事人类型：TS_BZDM案件类型非首次执行KIND=000004 首次执行KIND=01001")
    private String participantType;

    @ApiModelProperty("当事人诉讼地位，TS_BZDM,民事一审KIND=020008（只能使用15_020008-1、15_020008-2、15_020008-5）")
    private String statusInLitigation;

    @ApiModelProperty("单位名称")
    @NotBlank(message = "单位名称不能为空")
    private String orgName;

    @ApiModelProperty("统一社会信用代码：案件类型为首次执行时必填，非首次执行仅诉讼地位为原告时必填")
    private String orgCode;

    @ApiModelProperty("国家或地区，TS_BZDM案件类型非首次执行KIND=GB0006，首次执行KIND=00004")
    @NotBlank(message = "国家或地区不能为空")
    private String nationality;

    @ApiModelProperty("邮政编码")
    private String postCode;

    @ApiModelProperty("送达方式，TS_DM，诉讼地位为原告时必填，电子送达（1）邮寄送达（2）")
    private Integer serviceType;

    @ApiModelProperty("机构所在地")
    @NotBlank(message = "机构所在地不能为空")
    private String orgAddress;

    @ApiModelProperty("送达地址，诉讼地位为原告时必填")
    private String serviceAddress;

    @ApiModelProperty("法定代表人：案件类型为首次执行时必填，非首次执行仅诉讼地位为原告时必填")
    private String participantName;

    @ApiModelProperty("代表人证件种类，TS_BZDM案件类型非首次执行KIND=000008，首次执行KIND=00015案件类型为首次执行时必填，非首次执行仅诉讼地位为原告时必填")
    private String cardType;

    @ApiModelProperty("代表人证件号码，案件类型为首次执行时必填，非首次执行仅诉讼地位为原告时必填")
    private String cardNo;

    @ApiModelProperty("联系方式，诉讼地位为原告必填，仅支持手机号码")
    private String phone;

    @ApiModelProperty("职务")
    private String duty;

    @ApiModelProperty("电子邮箱，送达方式为电子送达时必填")
    private String email;

    @ApiModelProperty("代理人信息")
    private List<DsrdlrxxInfoDto> agentList;
}
