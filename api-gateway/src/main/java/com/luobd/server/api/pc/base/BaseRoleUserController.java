package com.luobd.server.api.pc.base;

import com.luobd.server.base.roles.dto.UserRolePageDTO;
import com.luobd.server.base.roles.input.AddUserRoleInput;
import com.luobd.server.base.roles.input.UserRolePageInput;
import com.luobd.server.base.roles.service.ICoreUserRoleService;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.entities.ResponsePageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags = "基础数据 - 角色用户")
@RequestMapping(value = "/api/core/roleUsers")
public class BaseRoleUserController {


    @Resource
    private ICoreUserRoleService coreUserRoleService;


    @PostMapping(value = "/add")
    @ApiOperation(value = "添加角色用户")
    public ResponseData<Boolean> add(@RequestBody @Valid AddUserRoleInput input) {
        return coreUserRoleService.add(input);
    }


    @GetMapping(value = "/delete")
    @ApiOperation(value = "删除角色用户")
    public ResponseData<Boolean> delete(@RequestParam Long id) {
        return coreUserRoleService.delete(id);
    }


    @PostMapping(value = "/page")
    @ApiOperation(value = "分页查询")
    public ResponsePageData<UserRolePageDTO> page(@RequestBody @Valid UserRolePageInput input) {
        return coreUserRoleService.page(input);
    }


}
