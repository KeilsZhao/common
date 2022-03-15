package com.bzfar.vo.caseSearch.zxNewCaseInfo.mock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "mock数据执行代理人详情信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MockZxDlrDetail {

    @ApiModelProperty("流水号")
    private String lsh;

    @ApiModelProperty("当事人序号")
    private String dsrxh;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("律师资格证号")
    private String zjh;

}
