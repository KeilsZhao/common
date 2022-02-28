package com.bzfar.vo.qichun;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fimipeler
 */
@ApiModel("蕲春接口返回")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVo {

    @ApiModelProperty("返回码")
    private String success;

    @ApiModelProperty("返回信息")
    private String message;

}
