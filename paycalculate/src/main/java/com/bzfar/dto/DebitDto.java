package com.bzfar.dto;

import com.bzfar.enums.DelayTimeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * @author Administrator
 */
@ApiModel("借贷计算器请求参数")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DebitDto {

    @ApiModelProperty("本金金额")
    private String fee;

    @ApiModelProperty("开始时间,时间格式：yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String beginTime;

    @ApiModelProperty("结束时间")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String endTime;

    @ApiModelProperty("利率，不用传%")
    private String rate;

}
