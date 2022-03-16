package com.bzfar.vo.caseSearch.zxNewCaseInfo.mock;

import com.bzfar.vo.caseSearch.newCaseInfo.mock.MockDsrDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author Administrator
 */
@ApiModel(value = "mock数据执行案件当事人信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MockZxDsrInfo {

    @ApiModelProperty("执行当事人数据")
    private List<MockZxDsrDetail> data;
}
