package com.luobd.server.chatglm;

import lombok.Data;

@Data
public class ImageRequestModel {

    /**
     * 模型名称  必填
     */
    private String model;


    /**
     * 提示信息  必填
     */
    private String prompt;


    /**
     * 终端的用户id  非必填
     */
    private String user_id;






}
