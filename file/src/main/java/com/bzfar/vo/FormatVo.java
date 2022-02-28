package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeSet;

/**
 * @author ""
 * @Description FormatVo
 * @Date 2021/11/29 11:24
 */
@ApiModel("格式转换后的返回")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormatVo {

    @ApiModelProperty("存储的网络地址")
    private String httpUrl;

    @ApiModelProperty("图片地址（pdf转图片多张）")
    private TreeSet<String> imgUrl;

}
