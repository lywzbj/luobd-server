package com.luobd.server.base.core.input;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luobd.server.base.core.entity.CoreCategory;
import com.luobd.server.common.utils.SnowIdWorker;
import com.luobd.server.mybatis.entities.BaseInput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ApiModel(description = "分类创建表单")
public class CreateCategoryInput implements BaseInput<CoreCategory> {

    @ApiModelProperty(value = "分类名称",required = true)
    @NotBlank(message = "请指定分类名称")
    private String categoryName;

    @ApiModelProperty(value = "父级id")
    private Long parentId;

    @ApiModelProperty(value = "类型",required = true)
    @NotBlank(message = "请指定分类类型")
    private String type;


    @ApiModelProperty(value = "备注")
    private String remark;


    @Override
    public QueryWrapper<CoreCategory> getRepeatQuery() {
        QueryWrapper<CoreCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("categoryName",this.getCategoryName());
        queryWrapper.eq("type",this.getType());
        queryWrapper.eq(this.getParentId() != null,"parentId",this.getParentId());
        return queryWrapper;
    }

    @Override
    public CoreCategory toEntity() {
        CoreCategory coreCategory = new CoreCategory();
        coreCategory.setId(SnowIdWorker.nextId());
        coreCategory.setCategoryName(this.getCategoryName());
        coreCategory.setRemark(this.getRemark());
        coreCategory.setType(this.getType());
        return coreCategory;
    }
}
