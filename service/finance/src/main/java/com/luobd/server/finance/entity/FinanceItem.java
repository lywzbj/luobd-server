package com.luobd.server.finance.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
 * @since 2024-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FinanceItem对象", description="")
public class FinanceItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    private Integer status;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("updateTime")
    private LocalDateTime updateTime;

    @TableLogic
    private Boolean deleted;

    @TableField("occurTime")
    private LocalDateTime occurTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField("categoryId")
    private Long categoryId;

    @TableField("categoryName")
    private String categoryName;

    private BigDecimal amount;

    @TableField("createUserId")
    private Long createUserId;

    @TableField("updateUserId")
    private Long updateUserId;


}
