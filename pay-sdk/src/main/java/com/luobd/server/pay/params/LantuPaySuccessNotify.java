package com.luobd.server.pay.params;

import lombok.Data;

@Data
public class LantuPaySuccessNotify {

    private String code;
    private String mch_id;
    private String out_trade_no;
    private String total_fee;
    private String pay_no;
    private String trade_type;
    private String timestamp;
    private String notify_url;
    private String attach;
    private String time_expire;
    private String pay_channel;
    private String sign;
    private String success_time;
    private String openid;



}
