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
@ApiModel("材料信息")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClxxDto {

    @ApiModelProperty("预约信息ID")
    @NotBlank(message = "预约信息ID不能为空")
    private String cYyxxid;

    @ApiModelProperty("文件名称（必填）")
    @NotBlank(message = "文件名称不能为空")
    private String cWjmc;

    @ApiModelProperty("材料存储路径（必填）")
    @NotBlank(message = "材料存储路径不能为空")
    private String cTpProtocol;

    @ApiModelProperty("文件类型（必填）")
    private Integer nWjlx;
}
