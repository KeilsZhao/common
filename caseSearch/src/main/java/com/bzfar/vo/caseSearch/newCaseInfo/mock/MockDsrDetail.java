package com.bzfar.vo.caseSearch.newCaseInfo.mock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Administrator
 */
@ApiModel(value = "mock数据当事人详情信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MockDsrDetail {

    @ApiModelProperty("流水号")
    private String lsh;

    @ApiModelProperty("当事人标识")
    private String xh;

    @ApiModelProperty("当事人类型：1 自然人 2 法人")
    private String type;

    @ApiModelProperty("当事人地位：1 原告 2 被告")
    private String dw;

    @ApiModelProperty("姓名，法人时为公司名称")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("出生日期")
    private String birthday;

    @ApiModelProperty("名族")
    private String mz;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("身份证号，法人时为社会信用代码")
    private String zjh;

    @ApiModelProperty("联系方式")
    private String sjh;
}
