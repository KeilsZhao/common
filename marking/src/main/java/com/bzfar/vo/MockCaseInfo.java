package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@ApiModel(value = "卷宗查询-mock数据案件信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MockCaseInfo {

    @ApiModelProperty("案件信息数据")
    private List<MockCaseDetail> data;

}
