package com.luobd.server.cash.core.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CashItem对象", description="")
public class CashItem implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId
    private Long id;


    @TableField(value = "status",fill = FieldFill.INSERT)
    private Integer status;

    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "updateTime",fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    private Boolean deleted;

    @TableField("projectName")
    private String projectName;

    @TableField("projectDate")
    private LocalDate projectDate;

    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField("projectId")
    private Long projectId;


    @TableField(value = "createUserId",fill = FieldFill.INSERT)
    private Long createUserId;


    @TableField(value = "updateUserId",fill = FieldFill.UPDATE)
    private Long updateUserId;

    @TableField("cashUserName")
    private String cashUserName;

    private BigDecimal amount;


}
