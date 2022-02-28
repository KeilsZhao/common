package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel("案件列表")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaseListVo {

    @ApiModelProperty("列表数据")
    private List<CaseVo> caseVos;

}
