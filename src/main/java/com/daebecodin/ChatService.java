package com.daebecodin;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

/**
 * This service class is tasked with taking the data received
 * from the client and getting an output from the model
 */
@Service
public class ChatService {

    private final ChatModel chatModel; //instantiating the chatModel class

    // injecting the chatModel class
    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    /**
     *
     * @param message is the message we receive from the client to pass to the model
     * @return we pass a message to the model
     */
    public String getResponse(String message) {
        return chatModel.call(message);
    }

   }
