package com.bzfar.dto.stand;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * ClassName:CaseFileList
 * Package:com.dto.jianyang
 * Description:
 *
 * @author:""
 * @since :2021/9/27 22:41
 */
@ApiModel("案件材料信息对象")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CaseFileList {

    @ApiModelProperty("材料类别，TS_DM KIND=FILECATEGORY_+案件类型")
    @NotBlank(message = "材料类别不能为空")
    private String fileCategory;

    @ApiModelProperty("材料原始名称")
    private String fileName;

    @ApiModelProperty("材料路径，通过文件上传接口获取")
    @NotBlank(message = "材料路径不能为空")
    private String filePath;

    @ApiModelProperty("校验值，文件校验hash")
    @JsonIgnore
    private String fileHash;

}
