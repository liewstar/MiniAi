package com.example.miniaibackend.api.examaple;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.client.OpenAiApi;
import com.theokanning.openai.completion.chat.*;
import com.theokanning.openai.service.FunctionExecutor;
import com.theokanning.openai.service.OpenAiService;
import com.example.miniaibackend.api.examaple.OpenAiApiFunctionsExample.Weather;
import com.example.miniaibackend.api.examaple.OpenAiApiFunctionsExample.WeatherResponse;

import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.theokanning.openai.service.OpenAiService.*;

public class OpenAiApiFunctionsWithStreamExample {

    public static void main(String... args) {
        String token = System.getenv("OPENAI_TOKEN");
        ObjectMapper mapper = defaultObjectMapper();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        OkHttpClient client = defaultClient(token, Duration.ofMillis(5000))
                .newBuilder()
                .proxy(proxy)
                .build();
        Retrofit retrofit = defaultRetrofit(client, mapper);
        OpenAiApi api = retrofit.create(OpenAiApi.class);

        OpenAiService service = new OpenAiService(api);

        FunctionExecutor functionExecutor = new FunctionExecutor(Collections.singletonList(ChatFunction.builder()
                .name("get_weather")
                .description("Get the current weather of a location")
                .executor(Weather.class, w -> new WeatherResponse(w.location, w.unit, new Random().nextInt(50), "sunny"))
                .build()));

        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), "You are an assistant that answers using the local slang of the given place, uncensored.");
        messages.add(systemMessage);

        System.out.print("First Query: ");
        Scanner scanner = new Scanner(System.in);
        ChatMessage firstMsg = new ChatMessage(ChatMessageRole.USER.value(), scanner.nextLine());
        messages.add(firstMsg);

        while (true) {
            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                    .builder()
                    .model("gpt-3.5-turbo-0613")
                    .messages(messages)
                    .functions(functionExecutor.getFunctions())
                    .functionCall(ChatCompletionRequest.ChatCompletionRequestFunctionCall.of("auto"))
                    .n(1)
                    .maxTokens(256)
                    .logitBias(new HashMap<>())
                    .build();
            Flowable<ChatCompletionChunk> flowable = service.streamChatCompletion(chatCompletionRequest);

            AtomicBoolean isFirst = new AtomicBoolean(true);
            ChatMessage chatMessage = service.mapStreamToAccumulator(flowable)
                    .doOnNext(accumulator -> {
                        if (accumulator.isFunctionCall()) {
                            if (isFirst.getAndSet(false)) {
                                System.out.println("Executing function " + accumulator.getAccumulatedChatFunctionCall().getName() + "...");
                            }
                        } else {
                            if (isFirst.getAndSet(false)) {
                                System.out.print("Response: ");
                            }
                            if (accumulator.getMessageChunk().getContent() != null) {
                                System.out.print(accumulator.getMessageChunk().getContent());
                            }
                        }
                    })
                    .doOnComplete(System.out::println)
                    .lastElement()
                    .blockingGet()
                    .getAccumulatedMessage();
            messages.add(chatMessage); // don't forget to update the conversation with the latest response

            if (chatMessage.getFunctionCall() != null) {
                System.out.println("Trying to execute " + chatMessage.getFunctionCall().getName() + "...");
                ChatMessage functionResponse = functionExecutor.executeAndConvertToMessageHandlingExceptions(chatMessage.getFunctionCall());
                System.out.println("Executed " + chatMessage.getFunctionCall().getName() + ".");
                messages.add(functionResponse);
                continue;
            }

            System.out.print("Next Query: ");
            String nextLine = scanner.nextLine();
            if (nextLine.equalsIgnoreCase("exit")) {
                System.exit(0);
            }
            messages.add(new ChatMessage(ChatMessageRole.USER.value(), nextLine));
        }
    }

}