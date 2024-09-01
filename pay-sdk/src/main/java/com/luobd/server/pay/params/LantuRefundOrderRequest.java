package com.luobd.server.pay.params;

import com.luobd.server.pay.utils.LanTuSignUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class LantuRefundOrderRequest {

    private String mch_id;
    private String out_trade_no;
    private String out_refund_no;
    private String refund_fee;
    private String timestamp;
    private String sign;



    public String sign(String partnerKey) {
        Map<String, String> params = new HashMap<>();
        params.put("mch_id", mch_id);
        params.put("out_trade_no", out_trade_no);
        params.put("out_refund_no", out_refund_no);
        params.put("refund_fee", refund_fee);
        params.put("timestamp", timestamp);
        return LanTuSignUtil.createSign(params, partnerKey);
    }


}
