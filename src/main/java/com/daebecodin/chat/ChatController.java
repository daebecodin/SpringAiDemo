package com.daebecodin.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * the controller will intercept a query at the respected endpoint.
 * the data received will be passed onto the service
 */

@RestController
public class ChatController {

    // instance of chatService
    private final ChatService chatService;
    private final ChatClient chatClient;


    // injecting chatService

    public ChatController(ChatService chatService, ChatClient.Builder chatClient) {
        this.chatService = chatService;
        this.chatClient = chatClient.build();

    }

    /**
     *
     * @param message is to be passed to the service as the prompt
     * @return we return the model's response to the message
     */
    @GetMapping(value = "ask")
    public String getResponse(@RequestParam String message) {
        return chatService.getResponse(message);
    }

    /**
     *
     * @param message is to be passed to the service as the prompt
     * @return we return the model's response to the message
     */
    @GetMapping(value = "ask-options")
    public String getResponseOptions(@RequestParam String message) {
        return chatService.getResponseOptions(message);
    }


    // ChatClient Object
    @GetMapping(value = "ask-chatclient/{prompt}")
    public String chatClientResponse(@PathVariable String prompt) {
        return chatService.chatClientGeneration(prompt) // submitting prompt
                .getResult() //retrieves response
                .getOutput() // as a
                .getText(); // String
    }

    // ChatResponse Object
    @GetMapping(value = "chatjson")
    public ChatResponse chatInJson(
            @RequestParam(
                    value = "message", // metadata
                    defaultValue = "Short reply explaining classes and interfaces in java" // default value for prompt
            )
                    String message
    ) {
        ChatResponse chatResponse = chatClient.prompt() // prompt method ready for input
                .user(message) // client submitting input
                .call() // completes the prompt
                .chatResponse(); // gets the response
        return chatResponse;

    }



}
