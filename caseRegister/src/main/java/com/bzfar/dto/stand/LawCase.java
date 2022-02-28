package com.bzfar.dto.stand;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

/**
 *
 * @author:""
 * @since :2021/9/27 10:58
 */
@ApiModel("简阳立案基本案件信息对象")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LawCase {

    @ApiModelProperty("案件状态：待审查(110)待立案,无特殊情况不传该值(130)转诉前调(170)")
    @JsonIgnore
    private Integer caseStatus;

    @ApiModelProperty("服务流水号")
    private String fwlsh;

    @ApiModelProperty("法院代码，6位法院代码")
    private String courtCode;

    @ApiModelProperty("来源渠道")
    @JsonIgnore
    private String caseSource;

    @ApiModelProperty("案件类型（接口获取），TS_DM,KIND=CASETYPE")
    private Integer caseType;

    @ApiModelProperty("案由（接口获取），TS_AY")
    private Integer caseCause;

    @ApiModelProperty("标的额，保留两位小数，非国家赔偿必填，首次执行标的种类为09_ZX0008-1必填，非诉财产保全时为申请保全数额必填")
    private BigDecimal caseAmount;

    @ApiModelProperty("诉讼费，保留两位小数")
    private BigDecimal caseCosts;

    @ApiModelProperty("地域涉及，TS_BZDM非首次执行KIND=GB0006,首次执行KIND=00004")
    private String district;

    @ApiModelProperty("适用程序，TS_BZDM 09法标kind=03201,15法标KIND=020030，浙江地区必填，其他地区非必填")
    private String process;

    @ApiModelProperty("诉讼请求，刑事一审时传犯罪事实和证据，首次执行时传案情说明，非诉财产保全不填写")
    private String claim;

    @ApiModelProperty("事实与理由")
    private String facts;

    @ApiModelProperty("确认送达地址")
    private String deliveryAddress;

    @ApiModelProperty("自诉类型，TS_BZDM案件类型为刑事一审时必填，KIND=010081")
    private String priProType;

    @ApiModelProperty("申请支付事项，TS_BZDM案件类型为支付令时必填，KIND=020170，多个值以,分隔")
    private String payMatter;

    @ApiModelProperty("管辖依据，TS_BZD案件类型为民事一审，支付令时必填，案件类型为民事一审、支付令时KIND=020005")
    private String jurisdictionalBasis;

    @ApiModelProperty("履约状态，案由为9178、9182、9193,9795时必填，TS_DM，KIND=KEEPAPPOINTMENTSTATUS")
    private Integer keepAppointmentStatus;

    @ApiModelProperty("首次提交时间，yyyy-MM-ddHH:mm:ss")
    private String firstCommitTime;

    @ApiModelProperty("调解协议签订日期")
    @JsonIgnore
    private String mediateAgreementSignDate;

    @ApiModelProperty("诉前调解案件案号代码，用来关联原案件要素信息")
    @JsonIgnore
    private String originalCaseCode;

    @ApiModelProperty("诉前调解案件案号")
    @JsonIgnore
    private String originalCaseNo;

}
