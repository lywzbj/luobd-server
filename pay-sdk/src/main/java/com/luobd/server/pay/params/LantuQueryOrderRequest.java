package com.luobd.server.pay.params;

import com.luobd.server.pay.utils.LanTuSignUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class LantuQueryOrderRequest {

    private String mch_id;

    private String out_trade_no;

    private String timestamp;

    private String sign;


    public String sign(String partnerKey) {
        Map<String, String> params = new HashMap<>();
        params.put("mch_id", mch_id);
        params.put("out_trade_no", out_trade_no);
        params.put("timestamp", timestamp);
        return LanTuSignUtil.createSign(params, partnerKey);
    }


}
