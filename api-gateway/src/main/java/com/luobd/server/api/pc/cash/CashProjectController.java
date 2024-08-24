package com.luobd.server.api.pc.cash;

import com.luobd.server.cash.core.dto.CashProjectPageDTO;
import com.luobd.server.cash.core.entity.CashProject;
import com.luobd.server.cash.core.input.CreateProjectInput;
import com.luobd.server.cash.core.input.ProjectPageInput;
import com.luobd.server.cash.core.input.UpdateProjectInput;
import com.luobd.server.cash.core.service.ICashProjectService;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.entities.ResponsePageData;
import com.luobd.server.common.entities.SelectDTO;
import com.luobd.server.common.permission.AccessPermission;
import com.luobd.server.permission.PermissionDefine;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cash/project")
@Api(tags = "礼金项目模块")
public class CashProjectController {

    @Resource
    private ICashProjectService cashProjectService;

    @PostMapping(value = "/page")
    @ApiOperation(value = "获取数据")
    public ResponsePageData<CashProjectPageDTO> page(@RequestBody @Valid ProjectPageInput input) {
        return cashProjectService.page(input);
    }


    @PostMapping(value = "/create")
    @ApiOperation(value = "新建项目")
    @AccessPermission(values = {PermissionDefine.PROJECT_CREATE})
    public ResponseData<Long> page(@RequestBody @Valid CreateProjectInput input) {
        return cashProjectService.create(input);
    }


    @PostMapping(value = "/update")
    @ApiOperation(value = "更新项目")
    @AccessPermission(values = {PermissionDefine.PROJECT_UPDATE})
    public ResponseData<Boolean> page(@RequestBody @Valid UpdateProjectInput input) {
        return cashProjectService.update(input);
    }

    @GetMapping(value = "/delete")
    @ApiOperation(value = "删除项目")
    @AccessPermission(values = {PermissionDefine.PROJECT_DELETE})
    public ResponseData<Boolean> page(@RequestParam(value = "id") Long id) {
        return cashProjectService.delete(id);
    }


    @GetMapping(value = "/select")
    @ApiOperation(value = "获取下拉框数据")
    public ResponseData<List<SelectDTO>> select() {
        return cashProjectService.select();
    }



}
