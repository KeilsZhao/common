package com.bzfar.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {

    @ApiModelProperty("邮件接收方")
    @NotBlank(message = "邮件接收方不能为空")
    private String to;

    @ApiModelProperty("邮件主题")
    @NotBlank(message = "邮件主题不能为空")
    private String subject;

    @ApiModelProperty("邮件内容")
    private String text;
}
