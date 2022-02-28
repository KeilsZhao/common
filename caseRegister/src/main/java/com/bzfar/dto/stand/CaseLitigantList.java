package com.bzfar.dto.stand;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * ClassName:CaseLitigantList
 * Package:com.dto.jianyang
 * Description:
 *
 * @author:""
 * @since :2021/9/27 17:03
 */
@ApiModel("案件参与人信息对象")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CaseLitigantList {

    @ApiModelProperty("自然人基本信息")
    private List<ZrrInfoDto> zrrInfoDtos;

    @ApiModelProperty("法人或非法人组织信息")
    private List<FrOrFfrzzInfoDto> frOrFfrzzInfoDtos;
}
