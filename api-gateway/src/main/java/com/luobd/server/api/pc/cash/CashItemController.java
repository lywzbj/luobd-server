package com.luobd.server.api.pc.cash;

import com.luobd.server.cash.core.entity.CashItem;
import com.luobd.server.cash.core.input.*;
import com.luobd.server.cash.core.service.ICashItemService;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.entities.ResponsePageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/cash/item")
@Api(tags = "礼金明细模块")
public class CashItemController {


    @Resource
    private ICashItemService cashItemService;



    @PostMapping(value = "/page")
    @ApiOperation(value = "获取数据")
    public ResponsePageData<CashItem> page(@RequestBody @Valid CashItemPageInput input) {
        return cashItemService.page(input);
    }


    @PostMapping(value = "/create")
    @ApiOperation(value = "新建明细")
    public ResponseData<Long> page(@RequestBody @Valid CreateCashItemInput input) {
        return cashItemService.create(input);
    }


    @PostMapping(value = "/update")
    @ApiOperation(value = "更新明细")
    public ResponseData<Boolean> page(@RequestBody @Valid UpdateCashItemInput input) {
        return cashItemService.update(input);
    }

}
