package com.example.miniaibackend.listener;

import com.alibaba.fastjson.JSON;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.util.SseHelper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Objects;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
public class ChatEventListener extends EventSourceListener {

    final SseEmitter sseEmitter;

    String last = "";
    @Setter
    Consumer<String> onComplate = s -> {

    };


    /**
     * {@inheritDoc}
     */
    @Override
    public void onOpen(EventSource eventSource, Response response) {

    }

    /**
     * {@inheritDoc}
     */
    @SneakyThrows
    @Override
    public void onEvent(EventSource eventSource, String id, String type, String data) {
        log.info("回答中：{}", data);
        if (data.equals("[DONE]")) {
            log.info("回答完成：" + last);
            onComplate.accept(last);
            SseHelper.complete(sseEmitter);
            return;
        }

        ChatCompletionResponse completionResponse = JSON.parseObject(data,
                ChatCompletionResponse.class); // 读取Json
        Message delta = completionResponse.getChoices().get(0).getDelta();
        String text = delta.getContent();
        if (text != null) {
            last += text;

            sseEmitter.send(delta);
        }
    }


    @Override
    public void onClosed(EventSource eventSource) {
        SseHelper.complete(sseEmitter);
    }


    @SneakyThrows
    @Override
    public void onFailure(EventSource eventSource, Throwable t, Response response) {
        if (Objects.isNull(response)) {
            return;
        }
        ResponseBody body = response.body();
        if (Objects.nonNull(body)) {
            sseEmitter.send("网络错误");
            log.error("OpenAI  sse连接body异常data：{}，异常：{}", body.string(), t);
        } else {
            sseEmitter.send("网络错误");
            log.error("OpenAI  sse连接异常data：{}，异常：{}", response, t);
        }

        eventSource.cancel();
        SseHelper.complete(sseEmitter);
    }
}
