package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@ApiModel("节点信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageInfoVo {

    @ApiModelProperty("页面中文名称")
    private String cnName;

//    @ApiModelProperty("页面英文名称")
//    private String enName;

    @ApiModelProperty("是否有返回按钮")
    private Boolean isReturn;

    @ApiModelProperty("是否展示")
    private Boolean isShow;

    @ApiModelProperty("节点信息")
    private Map<String, NodeInfoVo> nodeInfo;
}

