package com.bzfar.dto.qichun;

import com.bzfar.dto.other.Parent;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Fimipeler
 */
@ApiModel("蕲春立案请求参数")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QiChunRegisterDto extends Parent {

    @ApiModelProperty("当事人信息")
    private List<DsrxxDto> dsrxx;

    @ApiModelProperty("材料信息")
    private List<ClxxDto> clxx;

    @ApiModelProperty("预约信息")
    @JsonProperty("YYXX")
    private YyxxDto yyxx;

    @ApiModelProperty("代理人信息")
    private List<DlrxxDto> dlrxx;


}
