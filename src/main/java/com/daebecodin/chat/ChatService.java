package com.daebecodin.chat;

import org.springframework.ai.chat.client.ChatClient;
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
    private final ChatClient chatClient;

    // injecting the chatModel class
    public ChatService(ChatModel chatModel, ChatClient.Builder chatClient) {
        this.chatModel = chatModel;
        this.chatClient = chatClient.build();
    }

    /**
     *
     * @param message is the message we receive from the client to pass to the model
     * @return we pass a message to the model
     */
    public String getResponse(String message) {
        return chatModel.call(message);
    }


    public String getResponseOptions(String message) {
        ChatResponse response = chatModel.call( // creating a ChatResponse Object to use the cat model call function
                new Prompt(
                        message, // the prompt takes in the prompt message for the model
                        OpenAiChatOptions.builder() // and chat options
                                .model("gpt-4o") // model you want to use
                                .temperature(0.4) // randomness of the response
                                // comes with a bunch of other options to customize output.
                                // go back and check them out
                                .build()
                )
        );
        return response.getResult().getOutput().getText(  );
    }

    public ChatResponse chatClientGeneration(String prompt) {
        ChatResponse chatResponse = chatClient.prompt() // invoking prompt, ready to receive input
                .user(prompt) // invoking user, specifies the users input
                .call() //invoking call, completes prompt and submits to model
                .chatResponse();

        return chatResponse;
    }



   }
