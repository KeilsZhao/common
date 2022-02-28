package com.bzfar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("短信发送")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsDto implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty("手机号码")
    @NotNull(message = "手机号码不能为空")
    private String phoneNumber;

    @ApiModelProperty("短信内容")
    @NotNull(message = "短信内容不能为空")
    private String context;
}
