package com.bzfar.vo.caseSearch.zxNewCaseInfo.mock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@ApiModel(value = "mock数据执行案件信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MockZxCaseInfo {

    @ApiModelProperty("案件信息数据")
    private List<MockZxCaseDetail> data;

}
