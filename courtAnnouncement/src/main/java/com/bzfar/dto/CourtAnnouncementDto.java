package com.bzfar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * @author Administrator
 */
@ApiModel("开庭公告、开庭排期查询参数")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourtAnnouncementDto {

    @ApiModelProperty("开始时间,格式:YYYY-dd-MM HH:mm:ss")
    @JsonFormat(pattern = "YYYY-dd-MM HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;

    @ApiModelProperty("结束时间,格式:YYYY-dd-MM HH:mm:ss")
    @JsonFormat(pattern = "YYYY-dd-MM HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

}
