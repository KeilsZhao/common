package com.bzfar.dto.stand;

import com.bzfar.dto.other.Parent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * ClassName:RegisterCaseInfoDto
 * Package:com.dto.jianyang
 * Description:
 *
 * @author:""
 * @since :2021/9/27 11:39
 */
@ApiModel("立案信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterCaseInfoDto extends Parent {

    @ApiModelProperty("申请人")
    private Applicant applicant;

    @ApiModelProperty("案件基本信息")
    private LawCase lawCase;

    @ApiModelProperty("物权信息")
    @JsonIgnore
    private RealRightsSecurity realRightsSecurity;

    @ApiModelProperty("买卖合同纠纷扩展信息")
    @JsonIgnore
    private SalesContractDispute salesContractDispute;

    @ApiModelProperty("是否异步庭审")
    @JsonIgnore
    private LawCaseAdditionalInfo lawCaseAdditionalInfo;

    @ApiModelProperty("案件参与人信息对象")
    @JsonIgnore
    private List<Object> caseLitigantList;

    @ApiModelProperty("材料信息")
    private List<CaseFileList> caseFileList;

    @ApiModelProperty("当事人信息")
    private CaseLitigantList caseLitigantLists;

}
