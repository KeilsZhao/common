package com.bzfar.vo.caseSearch.zxNewCaseInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author Administrator
 */
@ApiModel(value = "执行案件信息",description = "标准版新版执行案件信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ZxCaseInfoVo {

    @ApiModelProperty("执行案件信息")
    private ZxCaseDetailInfoVo zxCaseDetailInfoVo;

    @ApiModelProperty("执行日志信息")
    private List<ZxCaseLogInfoVo> caseLogInfoVos;
}
