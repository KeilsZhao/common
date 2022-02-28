package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 刘成
 * @date 2021-6-7
 */
@ApiModel("卷宗")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DossierVo {

    @ApiModelProperty("卷宗类别")
    private String type;

    @ApiModelProperty("起始页码")
    private Integer startPageNum;

    @ApiModelProperty("序号")
    private String serialNum;

    @ApiModelProperty("材料名称")
    private String name;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("目录文件数据")
    private List<FileVo> fileList;
}
