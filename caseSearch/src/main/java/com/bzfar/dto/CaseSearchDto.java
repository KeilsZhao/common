package com.bzfar.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel("案件查询请求参数")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CaseSearchDto {

    @ApiModelProperty("案件编号")
    private String ah;

    @ApiModelProperty("证件号")
    private String zjh;

    @ApiModelProperty("身份证号")
    private String sfzh;

    @ApiModelProperty("年度")
    private String nd;

    @ApiModelProperty("代字")
    private String dz;

    @ApiModelProperty("序号")
    private String xh;

    @ApiModelProperty("输入框关键字：案件编号、承办人、当事人")
    private String key;

    @ApiModelProperty("法院代码，请求头获取")
    @JsonIgnore
    private String fydm;
}

