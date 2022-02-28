package com.bzfar.dto.stand;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

/**
 * ClassName:RealRightsSecurity
 * Package:com.dto.jianyang
 * Description:
 *
 * @author:"
 * @since :2021/9/27 11:38
 */
@ApiModel("简阳立案担保物权案件扩展信息对象")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RealRightsSecurity {

    @ApiModelProperty("担保数额，精确小数点后两位")
    @JsonIgnore
    private BigDecimal securityAmount;

    @ApiModelProperty("申请事项")
    @JsonIgnore
    private String applyItem;

    @ApiModelProperty("原实现担保物权案件案号")
    @JsonIgnore
    private String originalCaseNo;

}
