package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author Administrator
 */
@ApiModel(value = "卷宗查询-案件列表" , description = "卷宗查询-案件列表信息，卷宗详情页面")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CaseDetailInfoVo {

    @ApiModelProperty("代字")
    private String dz;

    @ApiModelProperty("案号")
    private String ah;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("案由")
    private String ay;

    @ApiModelProperty("立案日期")
    private String larq;

    @ApiModelProperty("承办部门")
    private String cbbm;

    @ApiModelProperty("承办人")
    private String cbr;

    @ApiModelProperty("适用程序")
    private String sycx;

    @ApiModelProperty("结案日期")
    private String jarq;

    @ApiModelProperty("原告")
    private List<CaseDsrInfoVo> yg;

    @ApiModelProperty("被告")
    private List<CaseDsrInfoVo> bg;
}
