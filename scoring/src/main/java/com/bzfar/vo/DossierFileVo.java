package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 刘成
 * @date 2021-6-8
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("卷宗文件获取")
public class DossierFileVo {

    @ApiModelProperty("图片地址")
    private String url;
}
