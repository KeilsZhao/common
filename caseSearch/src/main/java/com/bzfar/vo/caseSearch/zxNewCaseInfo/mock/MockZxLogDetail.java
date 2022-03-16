package com.bzfar.vo.caseSearch.zxNewCaseInfo.mock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Administrator
 */
@ApiModel(value = "mock数据执行日志详情信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MockZxLogDetail {

    @ApiModelProperty("日志内容")
    private String content;

    @ApiModelProperty("执行日期")
    private String zxrq;

    @ApiModelProperty("星期")
    private String week;


}
