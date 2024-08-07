package com.luobd.server.base.core.input;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luobd.server.base.core.entity.CoreCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@ApiModel(description = "分类创建表单")
public class UpdateCategoryInput extends CreateCategoryInput{


    @ApiModelProperty(value = "id",required = true)
    @NotNull(message = "id")
    private Long id;


    @Override
    public QueryWrapper<CoreCategory> getRepeatQuery() {
        QueryWrapper<CoreCategory> query = super.getRepeatQuery();
        query.notIn("id",this.getId());
        return query;
    }


    @Override
    public void updateEntity(CoreCategory coreCategory) {
        coreCategory.setType(this.getType());
        coreCategory.setCategoryName(this.getCategoryName());
        if(getParentId() != null) {
            coreCategory.setParentId(getParentId());
        }
        if(this.getRemark() != null && !this.getRemark().isEmpty()) {
            coreCategory.setRemark(this.getRemark());
        }
    }
}
