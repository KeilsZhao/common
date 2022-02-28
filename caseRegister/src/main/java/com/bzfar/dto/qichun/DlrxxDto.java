package com.bzfar.dto.qichun;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Fimipeler
 */
@ApiModel("代理人信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DlrxxDto {

    @ApiModelProperty("预约信息ID")
    private String cyxxid;

    @ApiModelProperty("联系方式")
    private String cLxfs;

    @ApiModelProperty("联系方式类型")
    private String nLxfslx;

    @ApiModelProperty("联系电话 联系方式类型为移动电话时")
    private String cLxdh;

    @ApiModelProperty("诉讼代理人其他联系方式 联系方式类型为非移动电话")
    private String cQtlxfs;

    @ApiModelProperty("证件号码")
    private String cZjhm;

    @ApiModelProperty("诉讼代理人职务")
    private String cZw;

    @ApiModelProperty("代理人证件类型")
    private String nZjlx;

    @ApiModelProperty("代理对象编号 当事人唯一标识")
    private String cDldxbh;

    @ApiModelProperty("代理人名称（必填）")
    @NotBlank(message = "代理人名称不能为空")
    private String cMc;

    @ApiModelProperty("类型")
    private String nLx;

    @ApiModelProperty("单位")
    private String cDw;

    @ApiModelProperty("种类 值：法定代理、指定代理、委托代理")
    private String nZl;
}
