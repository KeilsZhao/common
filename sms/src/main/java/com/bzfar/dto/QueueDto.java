package com.bzfar.dto;

import com.bzfar.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Ethons
 * @date 2021-7-8 11:32
 */
@ApiModel("消息队列请求头")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueueDto  implements Serializable {

    @ApiModelProperty("手机号码")
    @NotNull(message = "手机号码不能为空")
    private String phoneNumber;

    @ApiModelProperty("短信内容")
    @NotNull(message = "短信内容不能为空")
    private String context;

    @ApiModelProperty("短信网关")
    @NotNull(message = "短信")
    private BaseEnum baseEnum;
}
