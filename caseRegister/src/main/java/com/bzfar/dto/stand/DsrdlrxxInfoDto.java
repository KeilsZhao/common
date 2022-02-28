package com.bzfar.dto.stand;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * ClassName:DsrdlrxxInfoDto
 * Package:com.dto.jianyang
 * Description:
 *
 * @author:""
 * @since :2021/9/27 22:08
 */
@ApiModel("当事人代理人信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DsrdlrxxInfoDto {

    @ApiModelProperty("代理人类型，TS_BZDM案件类型非首次执行KIND=000006首次执行KIND=01010")
    @NotBlank(message = "代理人类型不能为空")
    private String participantType;

    @ApiModelProperty("代理人职务")
    private String duty;

    @ApiModelProperty("代理人姓名")
    @NotBlank(message = "代理人姓名不能为空")
    private String participantName;

    @ApiModelProperty("代理人性别")
    @NotBlank(message = "代理人性别不能为空")
    private String gender;

    @ApiModelProperty("代理人民族")
    private String nation;

    @ApiModelProperty("联系方式，原告代理人必填")
    private String phone;

    @ApiModelProperty("送达方式，TS_DM，原告代理人必填，电子送达（1）邮寄送达（2）")
    private Integer serviceType;

    @ApiModelProperty("送达地址，原告代理人必填")
    private String serviceAddress;

    @ApiModelProperty("送达方式为电子送达时必填")
    private String email;

    @ApiModelProperty("证件种类，原告代理人必填")
    private String cardType;

    @ApiModelProperty("证件号码，原告代理人必填")
    private String cardNo;

    @ApiModelProperty("执业证号，原告代理人必填")
    private String praCertificateNo;

    @ApiModelProperty("所在单位")
    private String orgName;

    @ApiModelProperty("邮政编码")
    private String postCode;

}
