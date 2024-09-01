package com.luobd.server;


import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import com.luobd.server.pay.utils.LanTuSignUtil;

import java.util.Map;
import java.util.TreeMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        long timestamp =  System.currentTimeMillis() / 1000;
        Map<String,String> params = new TreeMap<>();
        params.put("mch_id", "1684459485");
        params.put("out_trade_no", "Luobd784514");
        params.put("total_fee", "0.01");
        params.put("body", "QQ公仔");
        params.put("timestamp",  String.valueOf(timestamp));
        params.put("notify_url", "https://www.luobd.top");
        params.put("sign", LanTuSignUtil.createSign(params, "6033a1c0644e93a82666719df09b4029"));
        String signBody =  LanTuSignUtil.packageSign(params, false);
        HttpResponse execute = HttpUtil.createPost("https://api.ltzf.cn/api/wxpay/native")
                .header("content-type", "application/x-www-form-urlencoded")
                .body(signBody).execute();
        String string = execute.toString();



    }


}
