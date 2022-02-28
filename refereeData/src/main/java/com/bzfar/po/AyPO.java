package com.bzfar.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("案由")
@Data
public class AyPO {

    private String key;

    private String value;

    @ApiModelProperty("案由")
    private String text;
}
