package com.bzfar.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComNodeDto {

    @ApiModelProperty(value = "节点ID")
    private Integer id;

    @ApiModelProperty(value = "节点中文名称")
    private String cnName;

    @ApiModelProperty(value = "节点英文名称")
    private String enName;

    @ApiModelProperty(value = "节点类型")
    private String type;

    @ApiModelProperty(value = "节点路由")
    private String router;

    @ApiModelProperty(value = "父节点,一级节点（页面）父节点为-1")
    private Integer parentId;

    @ApiModelProperty(value = "是否有返回按钮，只针对父节点为-1的，1：有 2：没有")
    private String isReturn;

    @ApiModelProperty(value = "是否展示：1展示 2不展示")
    private String isShow;

    @ApiModelProperty(value = "节点顺序")
    private Integer sort;
}
