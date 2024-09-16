package com.luobd.server.api.pc.chatglm;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description
 * @create 2023-07-22 21:09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatProcessAggregate {

    /** 用户ID */
    private String openid;
    /** 默认模型 */
    private String model = "chatglm_turbo";
    /** 问题描述 */
    private List<MessageEntity> messages;

    public boolean isWhiteList(String whiteListStr) {
        String[] whiteList = whiteListStr.split(",");
        for (String whiteOpenid : whiteList) {
            if (whiteOpenid.equals(openid)) return true;
        }
        return false;
    }

    public OpenAiChannel getChannel(){
        return OpenAiChannel.getChannel(this.model);
    }

    public GenerativeModelVO getGenerativeModelVO() {
        switch (this.model) {
            case "dall-e-2":
            case "dall-e-3":
                return GenerativeModelVO.IMAGES;
            default:
                return GenerativeModelVO.TEXT;
        }
    }

}
