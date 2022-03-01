package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author Administrator
 */
@ApiModel(value = "卷宗查询-mock数据当事人信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MockDsrInfo {

    @ApiModelProperty("当事人数据")
    private List<MockDsrDetail> data;
}
