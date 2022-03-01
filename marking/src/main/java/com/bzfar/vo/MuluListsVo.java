package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel("目录结构")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MuluListsVo {

    @ApiModelProperty("目录数据")
    private List<MuluListVo> list;

}
