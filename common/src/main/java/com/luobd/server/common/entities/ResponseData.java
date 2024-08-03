package com.luobd.server.common.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(description = "统一实体")
public class ResponseData<T> {


    @ApiModelProperty(value = "错误码    200-正常   500-异常")
    private int code;



    @ApiModelProperty(value = "错误信息")
    private String msg = "success";



    @ApiModelProperty(value = "数据")
    private T data;




    public  static <T> ResponseData<T> success(T data) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setData(data);
        responseData.setCode(200);
        return  responseData;
    }

    public  static <T> ResponseData<T> error(String msg) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode(500);
        responseData.setMsg(msg);
        return  responseData;
    }


}
