package com.bzfar.vo.caseSearch.newCaseInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@ApiModel(value = "案件材料信息",description = "案件详情的材料信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CaseFileInfoVo {

    @ApiModelProperty("起诉状信息")
    private List<String> plaintiff;

    @ApiModelProperty("其他材料信息")
    private List<String> otherFile;

}
