package com.luobd.server.chatglm;

import lombok.Data;

import java.util.List;

@Data
public class RequestModel {

    /**
     * 所要調用的模型     必填
     */
    private String model;


    /**   必填
     *  {'role':'user','content':'你好'}
     */
    private List<Prompt> messages;

    /**
     *  請求id,不填則平平臺生成
     */
    private String request_id;


    /**
     * 是否流式返回， 默认为false 表示同步调用
     */
    private Boolean stream = false;


    @Data
    public static class Prompt {

        private String content;
        private String role;



    }







}
