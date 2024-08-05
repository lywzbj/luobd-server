package com.luobd.server.cash.core.service;

import com.luobd.server.cash.core.entity.CashProject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobd.server.cash.core.input.CreateProjectInput;
import com.luobd.server.cash.core.input.ProjectPageInput;
import com.luobd.server.cash.core.input.UpdateProjectInput;
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
public interface ICashProjectService extends IService<CashProject> {


   ResponseData<Boolean> delete(Long id);



  ResponseData<Boolean> batchDelete(List<Long> ids);


    ResponseData<Long> create(CreateProjectInput input);



    ResponseData<Boolean> update(UpdateProjectInput input);



    ResponsePageData<CashProject> page(ProjectPageInput input);









}
