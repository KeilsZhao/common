package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("裁判文书单条")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CpwsVo {

    @ApiModelProperty("文书类型")
    private String type;

    @ApiModelProperty("文书标题")
    private String title;

    @ApiModelProperty("案号")
    private String ah;

    @ApiModelProperty("文书时间")
    private String time;

    @ApiModelProperty("文书ID")
    private Integer id;

}
