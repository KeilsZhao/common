package com.bzfar.util;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("父子集集类")
public abstract class Tree {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("父集Id")
    private Integer parentId;

    @ApiModelProperty("子集列表")
    private List<Tree> child;

}
