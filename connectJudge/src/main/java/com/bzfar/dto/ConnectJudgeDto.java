package com.bzfar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Administrator
 */
@ApiModel("约见法官案件列表获取参数")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConnectJudgeDto {

    @ApiModelProperty("身份证号")
    private String sfzh;

}
