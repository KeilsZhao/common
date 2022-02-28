package com.bzfar.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@ApiModel("资源")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NodeInfoVo {

    @ApiModelProperty("节点名称：仅用于标识")
    private String cnName;

    @ApiModelProperty("英文名称")
    private String enName;

    @ApiModelProperty("节点路由")
    private String router;

    @ApiModelProperty("节点顺序")
    private Integer sort;

    @ApiModelProperty("是否展示")
    private Boolean isShow;

    @ApiModelProperty("节点资源")
    private Map<String,SourceInfoVo> sourceInfo;

}

