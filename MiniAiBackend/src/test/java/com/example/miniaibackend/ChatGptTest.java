//package com.example.miniaibackend;
//
//import com.example.miniaibackend.models.AcceptDTO;
//import com.example.miniaibackend.models.Preset;
//import com.example.miniaibackend.utils.ChatGptUtils;
//import com.plexpt.chatgpt.entity.chat.ChatCompletion;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//
//@SpringBootTest
//public class ChatGptTest {
//    @Test
//    public void test() {
//        List<Preset> list = new ArrayList<>();
//        Preset user = new Preset("user","我需要你写一份通用简历，每当我输入一个职业、项目名称时，你需要完成以下任务：\n" +
//                "task1: 列出这个人的基本资料，如姓名、出生年月、学历、面试职位、工作年限、意向城市等。一行列一个资料。\n" +
//                "task2: 详细介绍这个职业的技能介绍，至少列出10条\n" +
//                "task3: 详细列出这个职业对应的工作经历，列出2条\n" +
//                "task4: 详细列出这个职业对应的工作项目，列出2条。项目按照项目背景、项目细节、项目难点、优化和改进、我的价值几个方面来描述，多展示职业关键字。也可以体现我在项目管理、工作推进方面的一些能力。\n" +
//                "task5: 详细列出个人评价，100字左右\n" +
//                "你把以上任务结果按照以下Markdown格式输出：\n" +
//                "\n" +
//                "```\n" +
//                "### 基本信息\n" +
//                "<task1 result>\n" +
//                "\n" +
//                "### 掌握技能\n" +
//                "<task2 result>\n" +
//                "\n" +
//                "### 工作经历\n" +
//                "<task3 result>\n" +
//                "\n" +
//                "### 项目经历\n" +
//                "<task4 result>\n" +
//                "\n" +
//                "### 关于我\n" +
//                "<task5 result>\n" +
//                "\n" +
//                "```");
//        Preset assisent = new Preset("assistant","好的，请问您需要我为哪个职业编写通用简历呢？");
//        list.add(user);
//        list.add(assisent);
//
//        AcceptDTO acceptDTO = new AcceptDTO(1, 1, "sess-EUwZdNeIytbNdB8XpI8CzJH15kqdpfrciaKgnimI", "https://chat.hothub.link/","程序员", ChatCompletion.Model.GPT_3_5_TURBO.getName(), 2000, 0.5F, 0.5F, 0.5F,list);
//
//        SseEmitter response = ChatGptUtils.response(acceptDTO);
//        System.out.println(response);
//
//    }
//
//    public static void main(String[] args) {
//        List<Preset> list = new ArrayList<>();
//        Preset user = new Preset("user","我需要你写一份通用简历，每当我输入一个职业、项目名称时，你需要完成以下任务：\n" +
//                "task1: 列出这个人的基本资料，如姓名、出生年月、学历、面试职位、工作年限、意向城市等。一行列一个资料。\n" +
//                "task2: 详细介绍这个职业的技能介绍，至少列出10条\n" +
//                "task3: 详细列出这个职业对应的工作经历，列出2条\n" +
//                "task4: 详细列出这个职业对应的工作项目，列出2条。项目按照项目背景、项目细节、项目难点、优化和改进、我的价值几个方面来描述，多展示职业关键字。也可以体现我在项目管理、工作推进方面的一些能力。\n" +
//                "task5: 详细列出个人评价，100字左右\n" +
//                "你把以上任务结果按照以下Markdown格式输出：\n" +
//                "\n" +
//                "```\n" +
//                "### 基本信息\n" +
//                "<task1 result>\n" +
//                "\n" +
//                "### 掌握技能\n" +
//                "<task2 result>\n" +
//                "\n" +
//                "### 工作经历\n" +
//                "<task3 result>\n" +
//                "\n" +
//                "### 项目经历\n" +
//                "<task4 result>\n" +
//                "\n" +
//                "### 关于我\n" +
//                "<task5 result>\n" +
//                "\n" +
//                "```");
//        Preset assisent = new Preset("assistant","好的，请问您需要我为哪个职业编写通用简历呢？");
//        list.add(user);
//        list.add(assisent);
//
//        AcceptDTO acceptDTO = new AcceptDTO(1, 1, "sess-EUwZdNeIytbNdB8XpI8CzJH15kqdpfrciaKgnimI", "https://chat.hothub.link/","程序员", ChatCompletion.Model.GPT_3_5_TURBO.getName(), 2000, 0.5F, 0.5F, 0.5F,list);
//
//        SseEmitter response = ChatGptUtils.response(acceptDTO);
//        System.out.println(response);
//    }
//}
