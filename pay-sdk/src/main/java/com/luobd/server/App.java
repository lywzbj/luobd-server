package com.luobd.server;


import com.alibaba.fastjson.JSONObject;
import com.luobd.server.pay.params.LantuPostDataRequest;
import com.luobd.server.pay.response.PayResponse;
import com.luobd.server.pay.services.NativePayment;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {


        OkHttpClient okHttpClient = new OkHttpClient();

        Retrofit build = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://api.ltzf.cn/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        NativePayment nativePayment = build.create(NativePayment.class);


        LantuPostDataRequest request = LantuPostDataRequest.builder()
                .mch_id("1684459485")
                .out_trade_no("Luobd784514134ds")
                .total_fee("0.01")
                .body("QQ公仔")
                .timestamp(String.valueOf(System.currentTimeMillis() / 1000))
                .notify_url("https://www.luobd.top").build();
        Call<PayResponse> call = nativePayment.prePay(request.getMch_id(),
                request.getOut_trade_no(),
                request.getTotal_fee(),
                request.getBody(),
                request.getNotify_url(),
                request.getTimestamp(),
                request.sign("6033a1c0644e93a82666719df09b4029"));
        PayResponse execute = call.execute().body();
        System.out.println(JSONObject.toJSONString(execute));


//        long timestamp =  System.currentTimeMillis() / 1000;
//        Map<String,String> params = new TreeMap<>();
//        params.put("mch_id", "1684459485");
//        params.put("out_trade_no", "Luobd784514");
//        params.put("total_fee", "0.01");
//        params.put("body", "QQ公仔");
//        params.put("timestamp",  String.valueOf(timestamp));
//        params.put("notify_url", "https://www.luobd.top");
//        params.put("sign", LanTuSignUtil.createSign(params, "6033a1c0644e93a82666719df09b4029"));
//        String signBody =  LanTuSignUtil.packageSign(params, false);
//        HttpResponse execute = HttpUtil.createPost("https://api.ltzf.cn/api/wxpay/native")
//                .header("content-type", "application/x-www-form-urlencoded")
//                .body(signBody).execute();
//        String string = execute.toString();



    }


}
