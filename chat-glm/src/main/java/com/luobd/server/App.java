package com.luobd.server;



import cn.bugstack.chatglm.utils.BearerTokenUtils;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.luobd.server.chatglm.ImageRequestModel;
import com.luobd.server.chatglm.RequestModel;
import okhttp3.*;
import okhttp3.internal.sse.RealEventSource;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 *
 */
public class App {
    private static final String  API_KEY = "32f4994e4fbe74442ddc1e2d2c094d1f";
    private static final String  API_SECRET = "bMVMJ0Oz6Snr7UZV";
    private static final String  URL = "https://open.bigmodel.cn/api/paas/v4/chat/completions";
    public static void main( String[] args ) throws IOException, InterruptedException {
        ImageRequestModel model = new ImageRequestModel();
        model.setPrompt("a cute cat");
        model.setModel("cogview-3");
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://open.bigmodel.cn/api/paas/v4/images/generations")
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject.toJSONString(model)))
                .header("Authorization", "Bearer " + BearerTokenUtils.getToken(API_KEY, API_SECRET))
                .header("Content-Type", "application/json; charset=utf-8")
                .header("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)")
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if(!response.isSuccessful()){
            throw new RuntimeException("Request failed");
        }
        if (!response.isSuccessful()) {
            System.out.println("Request failed");
        }else {
            System.out.println(response.body().string());
        }
    }



    private static void sse() throws Exception{
        RequestModel model = new RequestModel();
        RequestModel.Prompt prompt = new RequestModel.Prompt();
        prompt.setRole("user");
        prompt.setContent("你好");
        model.setModel("GLM-4-Flash");
        model.setStream(true);
        model.setMessages(Lists.newArrayList(prompt));
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject.toJSONString(model)))
                .header("Authorization", "Bearer " + BearerTokenUtils.getToken(API_KEY, API_SECRET))
                .header("Content-Type", "application/json; charset=utf-8")
                .header("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)")
                .build();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RealEventSource realEventSource = new RealEventSource(request, new EventSourceListener() {
            @Override
            public void onEvent(EventSource eventSource, String id, String type, String data) {
                System.out.println(data);   // 请求到的数据
                if(data.equals("[DONE]")) {
                    countDownLatch.countDown();
                }
            }
        });
        realEventSource.connect(okHttpClient);
        countDownLatch.await();
        System.out.println("end");
    }



    private static void sync() throws Exception{
        RequestModel model = new RequestModel();
        RequestModel.Prompt prompt = new RequestModel.Prompt();
        prompt.setRole("user");
        prompt.setContent("你好");
        model.setModel("GLM-4-Flash");
        model.setStream(false);
        model.setMessages(Lists.newArrayList(prompt));
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject.toJSONString(model)))
                .header("Authorization", "Bearer " + BearerTokenUtils.getToken(API_KEY, API_SECRET))
                .header("Content-Type", "application/json; charset=utf-8")
                .header("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)")
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if(!response.isSuccessful()){
            throw new RuntimeException("Request failed");
        }
        if (!response.isSuccessful()) {
            System.out.println("Request failed");
        }else {
            System.out.println(response.body().string());
        }
    }


}
