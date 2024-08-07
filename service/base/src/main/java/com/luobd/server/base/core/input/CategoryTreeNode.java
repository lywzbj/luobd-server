package com.luobd.server.base.core.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel(description = "分类名称")
@Getter
@Setter
public class CategoryTreeNode {


    @ApiModelProperty(value = "id值")
    private Long id;


    @ApiModelProperty(value = "分类名称")
    private String categoryName;


    @ApiModelProperty(value = "父级id")
    private Long parentId;




    @ApiModelProperty(value = "下级节点")
    private List<CategoryTreeNode> child;



}
