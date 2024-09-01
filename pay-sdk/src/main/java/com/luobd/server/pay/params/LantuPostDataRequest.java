package com.luobd.server.pay.params;

import com.luobd.server.pay.utils.LanTuSignUtil;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.TreeMap;

@Data
@Builder
public class LantuPostDataRequest {

    private String mch_id;
    private String out_trade_no;
    private String total_fee;
    private String body;
    private String timestamp;
    private String notify_url;
    private String attach;
    private String time_expire;
    private String developer_appid;
    private String sign;


    public String toParamsString(String partnerKey) {
        this.sign = LanTuSignUtil.createSign(this.toMap(), partnerKey);
        return LanTuSignUtil.packageSign(this.toMap(), true);
    }

    private Map<String, String> toMap() {
        return new TreeMap<String,String>() {
            {
                put("mch_id", mch_id);
                put("out_trade_no", out_trade_no);
                put("total_fee", total_fee);
                put("body", body);
                put("timestamp", timestamp);
                put("notify_url", notify_url);
                put("attach", attach);
                put("time_expire", time_expire);
                put("developer_appid", developer_appid);
            }
        };
    }


    public String sign(String partnerKey) {
    	return LanTuSignUtil.createSign(this.toMap(), partnerKey);
    }

}
