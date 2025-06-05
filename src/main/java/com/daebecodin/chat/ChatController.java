package com.daebecodin.chat;

import org.springframework.web.bind.annotation.GetMapping;
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

    // injecting chatService
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
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

}
