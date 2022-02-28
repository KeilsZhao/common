package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel("当日取件与存件统计")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordCountTrendVO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty("存件次数")
    private Integer store;

    @ApiModelProperty("取件次数")
    private Integer pickUp;

    @ApiModelProperty("虚拟存件")
    private Integer storeTrend;

    @ApiModelProperty("虚拟取件")
    private Integer pickUpTrend;
}
