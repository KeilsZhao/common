package com.bzfar.vo.caseSearch.newCaseInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Administrator
 */
@ApiModel(value = "案件信息",description = "标准版新版案件信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CaseInfoVo {

    @ApiModelProperty("案件信息")
    private CaseDetailInfoVo caseDetailInfoVo;

    @ApiModelProperty("材料信息")
    private CaseFileInfoVo caseFileInfoVo;
}
