package com.luobd.server;


import com.alibaba.fastjson.JSONObject;
import com.luobd.server.pay.params.LantuPostDataRequest;
import com.luobd.server.pay.params.LantuQueryOrderRequest;
import com.luobd.server.pay.params.LantuRefundOrderRequest;
import com.luobd.server.pay.response.OrderQueryResponse;
import com.luobd.server.pay.response.PayResponse;
import com.luobd.server.pay.response.RefundOrderResponse;
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


//        LantuPostDataRequest request = LantuPostDataRequest.builder()
//                .mch_id("1684459485")
//                .out_trade_no("Luobd784514134ds")
//                .total_fee("0.01")
//                .body("QQ公仔")
//                .timestamp(String.valueOf(System.currentTimeMillis() / 1000))
//                .notify_url("https://www.luobd.top").build();
//        Call<PayResponse> call = nativePayment.prePay(request.getMch_id(),
//                request.getOut_trade_no(),
//                request.getTotal_fee(),
//                request.getBody(),
//                request.getNotify_url(),
//                request.getTimestamp(),
//                request.sign("6033a1c0644e93a82666719df09b4029"));
//        PayResponse execute = call.execute().body();
//        System.out.println(JSONObject.toJSONString(execute));

//
//        LantuQueryOrderRequest orderRequest = new LantuQueryOrderRequest();
//        orderRequest.setMch_id("1684459485");
//        orderRequest.setOut_trade_no("Luobd784514134ds");
//        orderRequest.setTimestamp(String.valueOf(System.currentTimeMillis() / 1000));
//        String sign = orderRequest.sign("6033a1c0644e93a82666719df09b4029");
//        orderRequest.setSign(sign);
//
//        System.out.println(sign);
//
//        Call<OrderQueryResponse> query = nativePayment.query(orderRequest.getMch_id(), orderRequest.getOut_trade_no(), orderRequest.getTimestamp(), orderRequest.getSign());
//
//
//        OrderQueryResponse execute1 = query.execute().body();
//
//        System.out.println(JSONObject.toJSONString(execute1));


        LantuRefundOrderRequest refundOrderRequest = new LantuRefundOrderRequest();
        refundOrderRequest.setMch_id("1684459485");
        refundOrderRequest.setOut_trade_no("Luobd784514");
        refundOrderRequest.setOut_refund_no("LuobdRefund784514");
        refundOrderRequest.setRefund_fee("0.01");
        refundOrderRequest.setTimestamp(String.valueOf(System.currentTimeMillis() / 1000));
        refundOrderRequest.setSign(refundOrderRequest.sign("6033a1c0644e93a82666719df09b4029"));


        Call<RefundOrderResponse> refund = nativePayment.refund(refundOrderRequest.getMch_id(),
                refundOrderRequest.getOut_trade_no(),
                refundOrderRequest.getOut_refund_no(),
                refundOrderRequest.getRefund_fee(),
                refundOrderRequest.getTimestamp(),
                refundOrderRequest.getSign());

        RefundOrderResponse body = refund.execute().body();
        System.out.println(JSONObject.toJSONString(body));



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
