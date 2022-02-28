package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel("存取件年/月/日统计")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountVO {

    @ApiModelProperty("统计的模式  0 -> 年 1 -> 季  2 -> 月 3 -> 周 4 -> 日")
    private Integer state;

    @ApiModelProperty("组织公司 0 -> 百智诚远  1 -> 创捷  2 -> 亚讯")
    private Integer company;

    @ApiModelProperty("数据统计")
    private List<CountDataVO> countDataVO;
}
