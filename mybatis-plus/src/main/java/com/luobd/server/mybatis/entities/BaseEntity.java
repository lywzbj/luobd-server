package com.luobd.server.mybatis.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {


    @ApiModelProperty(value = "id")
    private Long id;


    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    @ApiModelProperty(value = "状态   1-禁用   0-开启")
    private Integer status;



    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;



    @ApiModelProperty(value = "删除标记")
    private Boolean deleted;






}
