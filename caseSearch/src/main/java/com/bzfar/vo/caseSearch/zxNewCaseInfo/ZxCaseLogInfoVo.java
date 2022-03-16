package com.bzfar.vo.caseSearch.zxNewCaseInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author Administrator
 */
@ApiModel(value = "执行案件日志信息",description = "执行案件详情的日志信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ZxCaseLogInfoVo {

    @ApiModelProperty("日志内容")
    private String content;

    @ApiModelProperty("执行日期")
    private String zxrq;

    @ApiModelProperty("星期")
    private String week;

}
