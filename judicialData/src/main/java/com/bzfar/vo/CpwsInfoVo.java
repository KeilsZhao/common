package com.bzfar.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fimipeler
 */
@ApiModel("裁判文书详情")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpwsInfoVo {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("案号")
    private String ah;

    @ApiModelProperty("内容")
    private String content;

}
