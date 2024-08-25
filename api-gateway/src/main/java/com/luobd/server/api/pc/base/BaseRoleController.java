package com.luobd.server.api.pc.base;

import com.luobd.server.base.roles.entity.CoreRoles;
import com.luobd.server.base.roles.input.CreateRoleInput;
import com.luobd.server.base.roles.service.ICoreRolesService;
import com.luobd.server.common.entities.PageInput;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.entities.ResponsePageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags = "基础数据 - 角色权限")
@RequestMapping(value = "/api/core/roles")
public class BaseRoleController {

    @Resource
    private ICoreRolesService coreRolesService;

    @PostMapping(value = "/create")
    @ApiOperation(value = "创建角色")
    public ResponseData<Long> create(@RequestBody @Valid CreateRoleInput input) {
        return coreRolesService.create(input);
    }


    @GetMapping(value = "/delete")
    @ApiOperation(value = "删除角色")
    public ResponseData<Boolean> delete(@RequestParam Long id) {
        return coreRolesService.delete(id);
    }


    @PostMapping(value = "/page")
    @ApiOperation(value = "分页查询")
    public ResponsePageData<CoreRoles> page(@RequestBody @Valid PageInput input) {
        return coreRolesService.page(input);
    }



}
