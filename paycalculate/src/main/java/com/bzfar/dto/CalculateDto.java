package com.bzfar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel("缴费计算请求参数")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CalculateDto {

    @ApiModelProperty("计算类型")
    @NotBlank(message = "计算类型不能为空")
    private String type;

    @ApiModelProperty("费用")
    @NotBlank(message = "计算费用不能为空")
    private String fee;

}
