package com.bzfar.po;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value="DataInfo对象-裁判文书", description="")
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "judgment" , type = "doc" , shards = 1 , replicas = 0)
public class DataInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    private Integer id;

    @ApiModelProperty("案件名称")
    private String title;

    @ApiModelProperty("法院名称")
    @Field(store = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.Text)
    private String countName;

    @ApiModelProperty("案号")
    private String ah;

    @ApiModelProperty("案由")
    @Field(store = true, analyzer = "simple", searchAnalyzer = "simple", type = FieldType.Text)
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
