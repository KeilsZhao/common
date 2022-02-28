package com.bzfar.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("数据统计")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountDataVO{

    private static final long serialVersionUID=1L;

    @ApiModelProperty("当前月/日/时")
    private String date;

    @ApiModelProperty("当日取件与存件统计")
    private RecordCountVO recordCountVO;
}
