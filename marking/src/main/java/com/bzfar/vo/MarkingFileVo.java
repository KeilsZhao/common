package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("卷宗文件獲取")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarkingFileVo {

    @ApiModelProperty("圖片地址")
    private String image;
}
