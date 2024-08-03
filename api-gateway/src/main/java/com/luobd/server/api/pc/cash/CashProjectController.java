package com.luobd.server.api.pc.cash;

import com.luobd.server.common.entities.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cash/project")
@Api(tags = "礼金项目模块")
public class CashProjectController {


    @GetMapping(value = "/getData")
    @ApiOperation(value = "获取数据")
    public ResponseData<String> getData() {
        return ResponseData.success("data");
    }



}
