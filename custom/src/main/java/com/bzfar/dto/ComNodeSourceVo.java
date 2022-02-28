package com.bzfar.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@ApiModel("节点资源关联")
@NoArgsConstructor
@AllArgsConstructor
public class ComNodeSourceVo {

    @ApiModelProperty("节点ID")
    private Integer nodeId;

    @ApiModelProperty("资源ID")
    private String sourceId;

}
