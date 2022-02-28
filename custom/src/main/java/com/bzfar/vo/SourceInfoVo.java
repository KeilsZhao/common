package com.bzfar.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("资源信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SourceInfoVo {

    @ApiModelProperty("名称-前端取值")
    private String value;

    @ApiModelProperty("名称-C#取值")
    private String name;

    @ApiModelProperty("是否显示")
    private Boolean isShow;

//    @ApiModelProperty("资源类型：1背景 2icon 3文字 4富文本 5链接 6英文 7电话号码 8视频 9音频 10 icon1,如果是富文本，value为富文本ID，需调接口查询")
//    private String type;

}

