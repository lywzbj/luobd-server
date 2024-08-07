package com.luobd.server.base.core.service;

import com.luobd.server.base.core.entity.CoreCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobd.server.base.core.input.CategoryPageInput;
import com.luobd.server.base.core.input.CategoryTreeNode;
import com.luobd.server.base.core.input.CreateCategoryInput;
import com.luobd.server.base.core.input.UpdateCategoryInput;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.entities.ResponsePageData;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-05
 */
public interface ICoreCategoryService extends IService<CoreCategory> {


   ResponseData<Boolean> delete(Long id);



  ResponseData<Boolean> batchDelete(List<Long> ids);



  ResponseData<Long> create(CreateCategoryInput input);



  ResponseData<Boolean> update(UpdateCategoryInput input);


  ResponsePageData<CoreCategory> page(CategoryPageInput input);




  ResponseData<List<CategoryTreeNode>> getTree(String type);





}
