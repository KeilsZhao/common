package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel("目录")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MuluListVo {

    @ApiModelProperty("卷宗类别")
    private String jzlb;

    @ApiModelProperty("p1")
    private String p1;

    @ApiModelProperty("序号")
    private String xh;

    @ApiModelProperty("材料名称")
    private String clbt;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("目录数据")
    private List<MuluVo> muluVo;

}
