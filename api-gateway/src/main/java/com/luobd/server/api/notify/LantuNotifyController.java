package com.luobd.server.api.notify;

import com.alibaba.fastjson.JSONObject;
import com.luobd.server.pay.params.LantuPaySuccessNotify;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notify/lantu")
@Api(tags = "蓝兔支付回调")
@Slf4j
public class LantuNotifyController {


    /**
     *      String code;
     *      String mch_id;
     *      String out_trade_no;
     *      String total_fee;
     *      String pay_no;
     *      String trade_type;
     *      String timestamp;
     *      String attach;
     *      String time_expire;
     *      String pay_channel;
     *      String sign;
     *      String success_time;
     *      String openid;
     */

    @PostMapping(value = "/payed",consumes = "application/x-www-form-urlencoded")
    @ApiOperation(value = "支付成功回调")
    public String payNotify(@RequestParam(value = "code") String code,
                            @RequestParam(value ="timestamp")String timestamp,
                            @RequestParam(value = "mch_id") String mch_id,
                            @RequestParam(value = "order_no") String orderNo,
                            @RequestParam(value = "out_trade_no") String out_trade_no,
                            @RequestParam(value = "pay_no") String pay_no,
                            @RequestParam(value = "total_fee") String total_fee,
                            @RequestParam("sign") String sign,
                            @RequestParam(value = "pay_channel") String pay_channel,
                            @RequestParam(value = "trade_type") String trade_type,
                            @RequestParam(value = "success_time") String successTime,
                            @RequestParam(value = "attach") String attach,
                            @RequestParam(value = "openid") String openid) {
        log.info("接收到蓝兔支付成功回调:");
        log.info("code:{},timestamp:{},mch_id:{},orderNo:{},out_trade_no:{},pay_no:{},total_fee:{},sign:{},pay_channel:{},trade_type:{},success_time:{},attach:{},openid:{}",
                code,timestamp,mch_id,orderNo,out_trade_no,pay_no,total_fee,sign,pay_channel,trade_type,successTime,attach,openid);
        return "SUCCESS";
    }

}
