package com.luobd.server.base.user.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Blob;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2024-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CoreUserInfo对象", description="")
public class CoreUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId
    private Long id;


    private Integer status;

    @TableField("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @TableField("updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    private Blob deleted;

    @TableField("birthDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birthDate;

    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField("userInfoId")
    private Long userInfoId;

    @TableField("trueName")
    private String trueName;

    private String email;

    @TableField("phoneNumber")
    private String phoneNumber;

    @TableField("accountId")
    private Long accountId;


}
