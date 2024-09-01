package com.luobd.server.pay.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RefundOrderResponse {
    private Long code;
    private Data data;
    private String msg;
    @JsonProperty("request_id")
    private String requestId;

    @lombok.Data
    public static class Data {
        @JsonProperty("mch_id")
        private String mchId;
        @JsonProperty("out_trade_no")
        private String outTradeNo;
        @JsonProperty("out_refund_no")
        private String outRefundNo;
        @JsonProperty("order_no")
        private String orderNo;
        @JsonProperty("pay_refund_no")
        private String payRefundNo;
    }

}
