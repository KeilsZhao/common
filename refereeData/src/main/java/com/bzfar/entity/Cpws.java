package com.bzfar.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ethons
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Cpws对象", description="")
public class Cpws implements Serializable {

    private static final long serialVersionUID=1L;

   // @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String docId;

    private String source;

    private Date crawlTime;

    @ApiModelProperty(value = "爬取是否成功标志 0/1 成功/失败")
    private Integer status;

    private String channel;

    @ApiModelProperty(value = "是否清洗标志 0/1 成功/失败")
    private Integer etl;


}
