package com.luobd.server.api.pc.base;

import com.luobd.server.base.core.entity.CoreCategory;
import com.luobd.server.base.core.input.CategoryPageInput;
import com.luobd.server.base.core.input.CategoryTreeNode;
import com.luobd.server.base.core.input.CreateCategoryInput;
import com.luobd.server.base.core.input.UpdateCategoryInput;
import com.luobd.server.base.core.service.ICoreCategoryService;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.entities.ResponsePageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "基础数据 - 分类模块")
@RequestMapping(value = "/api/core/category")
public class BaseCategoryController {

    @Resource
    private ICoreCategoryService coreCategoryService;



    @PostMapping(value = "/create")
    @ApiOperation(value = "创建分类")
    public ResponseData<Long> create(@RequestBody @Valid CreateCategoryInput input) {
        return coreCategoryService.create(input);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新分类")
    public ResponseData<Boolean> update(@RequestBody @Valid UpdateCategoryInput input) {
        return coreCategoryService.update(input);
    }


    @PostMapping(value = "/page")
    @ApiOperation(value = "分页查询")
    public ResponsePageData<CoreCategory> page(@RequestBody @Valid CategoryPageInput input) {
        return coreCategoryService.page(input);
    }


    @GetMapping(value = "/tree")
    @ApiOperation(value = "获取树形结构")
    public ResponseData<List<CategoryTreeNode>> tree(@RequestParam(value = "type") String type) {
        return coreCategoryService.getTree(type);
    }









}
