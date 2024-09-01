package com.luobd.server.pay.services;

import com.luobd.server.pay.response.PayResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface NativePayment {



    @POST("/api/wxpay/native")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<PayResponse> prePay(@Field("mch_id")String mch_id,
                             @Field("out_trade_no")String out_trade_no,
                             @Field("total_fee")String total_fee,
                             @Field("body")String body,
                             @Field("notify_url")String notify_url,
                             @Field("timestamp")String timestamp,
                             @Field("sign")String sign);




}
