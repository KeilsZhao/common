package com.bzfar.vo.caseSearch.zxNewCaseInfo.mock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@ApiModel(value = "mock数据执行日志列表信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MockZxLogInfo {

    @ApiModelProperty("材料信息")
    private List<MockZxLogDetail> data;

}
