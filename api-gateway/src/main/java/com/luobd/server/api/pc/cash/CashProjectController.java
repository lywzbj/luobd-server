package com.luobd.server.api.pc.cash;

import com.luobd.server.cash.core.entity.CashProject;
import com.luobd.server.cash.core.input.CreateProjectInput;
import com.luobd.server.cash.core.input.ProjectPageInput;
import com.luobd.server.cash.core.input.UpdateProjectInput;
import com.luobd.server.cash.core.service.ICashProjectService;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.entities.ResponsePageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/cash/project")
@Api(tags = "礼金项目模块")
public class CashProjectController {


    @Resource
    private ICashProjectService cashProjectService;



    @PostMapping(value = "/page")
    @ApiOperation(value = "获取数据")
    public ResponsePageData<CashProject> page(@RequestBody @Valid ProjectPageInput input) {
        return cashProjectService.page(input);
    }


    @PostMapping(value = "/create")
    @ApiOperation(value = "新建项目")
    public ResponseData<Long> page(@RequestBody @Valid CreateProjectInput input) {
        return cashProjectService.create(input);
    }


    @PostMapping(value = "/update")
    @ApiOperation(value = "新建项目")
    public ResponseData<Boolean> page(@RequestBody @Valid UpdateProjectInput input) {
        return cashProjectService.update(input);
    }

}
