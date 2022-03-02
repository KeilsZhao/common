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
@ApiModel("延迟执行请求参数")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DelayDto {

    @ApiModelProperty("金钱债务额")
    private String total;

    @ApiModelProperty("已履行金额")
    private String execute;

    @ApiModelProperty("开始时间,时间格式：yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty("年月日选择")
    private DelayTimeEnum delayTime;

    @ApiModelProperty("利率，不用传%")
    private String rate;
}
