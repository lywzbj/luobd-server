package com.luobd.server.pay.services;

import com.luobd.server.pay.response.OrderQueryResponse;
import com.luobd.server.pay.response.PayResponse;
import com.luobd.server.pay.response.RefundOrderResponse;
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



    @POST(value = "/api/wxpay/get_pay_order")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<OrderQueryResponse> query(@Field("mch_id") String mch_id,
                                   @Field("out_trade_no")String out_trade_no,
                                   @Field("timestamp")String timestamp,
                                   @Field("sign")String sign);



    @POST(value = "/api/wxpay/refund_order")
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<RefundOrderResponse> refund(@Field("mch_id") String mch_id,
                                     @Field("out_trade_no")String out_trade_no,
                                     @Field("out_refund_no")String out_refund_no,
                                     @Field("refund_fee")String refund_fee,
                                     @Field("timestamp")String timestamp,
                                     @Field("sign")String sign);




}
