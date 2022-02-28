package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("开庭排期返回信息")
public class AnnouncementVo {

    @ApiModelProperty("开庭排期案件信息")
    private List<CourtAnnouncementVo> data;

}
