package com.bzfar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

/**
 * @author Ethons
 * @date 2021-7-8 11:32
 */
@ApiModel("附件")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnexDto {

    @ApiModelProperty("文件流")
    private InputStream is;

    @ApiModelProperty("文件名称")
    private String name;
}
