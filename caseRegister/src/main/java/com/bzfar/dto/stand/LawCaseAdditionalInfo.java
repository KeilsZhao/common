package com.bzfar.dto.stand;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * ClassName:LawCaseAdditionalInfo
 * Package:com.dto.jianyang
 * Description:
 *
 * @author:""
 * @since :2021/9/27 17:01
 */
@ApiModel("案件附加信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LawCaseAdditionalInfo {

    @ApiModelProperty("是否异步庭审")
    @JsonIgnore
    private Integer sfybts;

}
