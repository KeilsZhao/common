package com.bzfar.vo.caseSearch.zxNewCaseInfo.mock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@ApiModel(value = "mock数据执行代理人信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MockZxDlrInfo {

    @ApiModelProperty("执行代理人信息")
    private List<MockZxDlrDetail> data;
}
