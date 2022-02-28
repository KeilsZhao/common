package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@ApiModel("页面信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllPageVo {

    @ApiModelProperty("所有页面")
    private Map<String, PageInfoVo> pageVos;

}

