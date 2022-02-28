package com.bzfar.vo.caseSearch.newCaseInfo.mock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@ApiModel(value = "mock数据材料信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MockFileInfo {

    @ApiModelProperty("材料信息")
    private List<MockFileDetail> data;

}
