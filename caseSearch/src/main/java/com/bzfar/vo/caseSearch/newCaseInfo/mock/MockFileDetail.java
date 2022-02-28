package com.bzfar.vo.caseSearch.newCaseInfo.mock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "mock数据材料详情信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MockFileDetail {

    @ApiModelProperty("文件路径")
    private String path;

    @ApiModelProperty("文件类型： 1 起诉状 2其他材料")
    private String type;

}
