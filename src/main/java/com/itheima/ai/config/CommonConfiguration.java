package com.itheima.ai.config;

import com.itheima.ai.constans.SystemConstants;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean
    public ChatMemory chatMemory(){
        return  new InMemoryChatMemory();
    }
    @Bean
        public ChatClient chatClient(OllamaChatModel model,ChatMemory chatMemory){
            return  ChatClient
                    .builder(model)
                    .defaultSystem("你是一个温柔的好ai，你叫大米")
                    .defaultAdvisors(
                            new SimpleLoggerAdvisor(),
                            new MessageChatMemoryAdvisor(chatMemory)

                    )
                    .build();
    }

    @Bean
    public ChatClient gameChatClient(OpenAiChatModel model, ChatMemory chatMemory){
        return  ChatClient
                .builder(model)
                .defaultSystem(SystemConstants.GAME_SYSTEM_PROMPT)
                .defaultAdvisors(
                        new SimpleLoggerAdvisor(),
                        new MessageChatMemoryAdvisor(chatMemory)

                )
                .build();
    }
}
