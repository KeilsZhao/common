package com.bzfar.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

//import org.springframework.data.elasticsearch.annotations.Document;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="DataInfo对象", description="")
//@Document(indexName = "data_info" , type = "data")
public class DataInfo implements Serializable {

    private static final long serialVersionUID=1L;

  //  @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("案件名称")
    private String title;

    @ApiModelProperty("法院名称")
    private String countName;

    @ApiModelProperty("案号")
    private String ah;

    @ApiModelProperty("案由")
    private String ay;

    @ApiModelProperty("案件类型")
    private String caseType;

    @ApiModelProperty("裁判日期")
    private String refereeTime;

    @ApiModelProperty("审判人")
    private String spr;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("文书类型")
    private String refereeType;

}
