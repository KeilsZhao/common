package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("用户存储记录")
@Data
public class UserCountVO {

    @ApiModelProperty("业务庭")
    private String hall;

    @ApiModelProperty("岗位")
    private String depart;

    @ApiModelProperty("姓名")
    private String trueName;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("使用状态")
    private String useState;

    @ApiModelProperty("存贮件信息")
    private List<CountDataVO> vo;
}
