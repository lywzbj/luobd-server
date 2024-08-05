package com.luobd.server.mybatis.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {


    @ApiModelProperty(value = "id")
    @TableId
    private Long id;


    @ApiModelProperty(value = "创建时间")
    @TableField(value = "createTime")
    private LocalDateTime createTime;


    @ApiModelProperty(value = "状态   1-禁用   0-开启")
    @TableField(value = "status")
    private Integer status;


    @ApiModelProperty(value = "更新时间")
    @TableField(value = "updateTime")
    private LocalDateTime updateTime;


    @ApiModelProperty(value = "删除标记")
    @TableField(value = "deleted")
    private Boolean deleted;

    @TableField("createUserId")
    private Long createUserId;

    @TableField("updateUserId")
    private Long updateUserId;




}
