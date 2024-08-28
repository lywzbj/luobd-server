package com.luobd.server.chat.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * @since 2024-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ChatRecord对象", description="")
public class ChatRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private Integer status;

    @TableField(value = "createTime", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "updateTime", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Boolean deleted;

    private String content;

    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(value = "createUserId", fill = FieldFill.INSERT)
    private Long createUserId;

    @TableField(value = "updateUserId", fill = FieldFill.UPDATE)
    private Long updateUserId;

    @TableField("chatType")
    private String chatType;

    @TableField("chatModel")
    private String chatModel;

    @TableField("conversationId")
    private Long conversationId;


}
