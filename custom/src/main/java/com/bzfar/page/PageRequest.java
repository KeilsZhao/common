package com.bzfar.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("分页查询")
public class PageRequest {

    /** 起始页 */
    @ApiModelProperty(value = "起始页, 默认为0" , example = "0")
    @NotNull(message = "起始页不能为空")
    private Integer pageNum = 0;

    /** 单页显示量 */
    @ApiModelProperty(value = "页面显示大小，默认为10" , example = "10")
    @NotNull(message = "页面显示大小不能为空")
    private Integer pageSize = 10;
}
