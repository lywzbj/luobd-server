package com.luobd.server.cash.core.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Blob;
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


    private Integer status;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("updateTime")
    private LocalDateTime updateTime;

    private Blob deleted;

    @TableField("projectName")
    private String projectName;

    @TableField("projectDate")
    private LocalDate projectDate;

    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField("projectId")
    private Long projectId;

    @TableField("cashUserName")
    private String cashUserName;

    private BigDecimal amount;


}
