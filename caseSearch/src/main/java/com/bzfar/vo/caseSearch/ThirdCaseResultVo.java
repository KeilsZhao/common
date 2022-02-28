package com.bzfar.vo.caseSearch;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("案件查询结果")
public class ThirdCaseResultVo {

    @ApiModelProperty("查询结果码")
    private String code;

    @ApiModelProperty("查询结果信息")
    private String message;

    @ApiModelProperty("案件具体信息")
    private List<ThirdCaseVo> data;

}
