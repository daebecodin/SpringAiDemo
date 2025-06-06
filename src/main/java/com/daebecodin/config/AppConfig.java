package com.daebecodin.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration // define this class for spring configuration
public class AppConfig {

    // injecting a default system message
    @Value("classpath:input.txt")
    Resource resource;

    // adding a default system message prompt
    @Bean // define this method as a spring bean
    public ChatClient chatClientBuilder(ChatClient.Builder chatClientBuilder) { // ChatClient method accepting a ChatClient.Builder obj
        return chatClientBuilder.defaultSystem(resource)
                .build();
        // return our system prompt
    }

    // you can also configure specific ChatClient beans for specific ai models
}
