package com.bzfar.vo.caseSearch.newCaseInfo.mock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@ApiModel(value = "mock数据代理人信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MockDlrInfo {

    @ApiModelProperty("代理人信息")
    private List<MockDlrDetail> data;
}
