package com.bzfar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("案由快速搜索")
@Data
public class DataListDto {

    @ApiModelProperty("案由")
    @NotBlank(message = "案由不能为空")
    private String ay;

    @ApiModelProperty("展示条数")
    @NotNull(message = "展示条数不能为空")
    private Integer max;
}
