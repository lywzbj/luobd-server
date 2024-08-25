package com.luobd.server.api.pc.finance;

import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.entities.ResponsePageData;
import com.luobd.server.finance.entity.FinanceItem;
import com.luobd.server.finance.input.CreateFianceItemInput;
import com.luobd.server.finance.input.FinancePageInput;
import com.luobd.server.finance.input.UpdateFinanceItemInput;
import com.luobd.server.finance.service.IFinanceItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/finance/item")
@Api(tags = "财务 - 记录模块")
public class FinanceRecordController {


    @Resource
    private IFinanceItemService financeItemService;

    @PostMapping(value = "/create")
    @ApiOperation(value = "创建记录")
    public ResponseData<Long> create(@RequestBody @Valid CreateFianceItemInput input) {
        return financeItemService.create(input);
    }


    @GetMapping(value = "/delete")
    @ApiOperation(value = "删除记录")
    public ResponseData<Boolean> delete(@RequestParam Long id) {
        return financeItemService.delete(id);
    }


    @PostMapping(value = "/update")
    @ApiOperation(value = "更新记录")
    public ResponseData<Boolean> update(@RequestBody @Valid UpdateFinanceItemInput input) {
        return financeItemService.update(input);
    }

    @PostMapping(value = "/page")
    @ApiOperation(value = "分页查询")
    public ResponsePageData<FinanceItem> page(@RequestBody @Valid FinancePageInput input) {
        return financeItemService.page(input);
    }
}
