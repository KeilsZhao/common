package com.bzfar.dto.stand;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * ClassName:ZrrInfoDto
 * Package:com.dto.jianyang
 * Description:
 *
 * @author:""
 * @since :2021/9/27 17:04
 */
@ApiModel("自然人基本信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ZrrInfoDto {

    @ApiModelProperty("当事人类型：TS_BZDM案件类型非首次执行KIND=000004 首次执行KIND=01001")
    private String participantType;

    @ApiModelProperty("当事人诉讼地位，TS_BZDM,民事一审KIND=020008（只能使用15_020008-1、15_020008-2、15_020008-5）")
    private String statusInLitigation;

    @ApiModelProperty("姓名")
    private String participantName;

    @ApiModelProperty("证件类型：TS_BZDM案件类型非首次执行KIND=000008，首次执行KIND=00015")
    private String cardType;

    @ApiModelProperty("证件号码")
    private String cardNo;

    @ApiModelProperty("民族，TS_BZDM案件类型非首次执行KIND=GB0002，首次执行KIND=00005，案件类型为首次执行时必填，非首次执行仅诉讼地位为原告时必填")
    private String nation;

    @ApiModelProperty("性别，TS_BZDM案件类型非首次执行KIND=GB0001，首次执行KIND=00003")
    private String gender;

    @ApiModelProperty("联系方式，诉讼地位为原告必填，仅支持手机号码")
    private String phone;

    @ApiModelProperty("国籍，TS_BZDM案件类型非首次执行KIND=GB0006，首次执行KIND=00004，案件类型为首次执行时必填，非首次执行仅诉讼地位为原告时必填")
    private String nationality;

    @ApiModelProperty("户籍所在地")
    private String domicilePlace;

    @ApiModelProperty("居住地址")
    @NotBlank(message = "居住地址不能为空")
    private String participantAddress;

    @ApiModelProperty("送达方式，TS_DM，诉讼地位为原告时必填，电子送达（1）邮寄送达（2）")
    private Integer serviceType;

    @ApiModelProperty("送达地址，诉讼地位为原告时必填")
    private String serviceAddress;

    @ApiModelProperty("电子邮箱，送达方式为电子送达时必填")
    private String email;

    @ApiModelProperty("出生日期，诉讼地位为原告时必填，yyyy-MM-dd")
    private String birthday;

    @ApiModelProperty("邮政编码")
    private String postCode;

    @ApiModelProperty("政治面貌，TS_BZDM（执行KIND=00008其余KIND=GB0003）")
    private String politicalVisage;

    @ApiModelProperty("代理人信息")
    private List<DsrdlrxxInfoDto> agentList;

}
