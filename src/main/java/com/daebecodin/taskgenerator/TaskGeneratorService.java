package com.daebecodin.taskgenerator;

import ch.qos.logback.core.pattern.color.ANSIConstants;
import com.daebecodin.model.Answer;
import com.daebecodin.model.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class TaskGeneratorService implements  TaskGenerator{

    private final ChatClient chatClient;

    public TaskGeneratorService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @Override
    public Answer generateTasks(Question question) {

        // because we made answer and question objects, when we pass in the question we must invoke its method to get the string value
        // then we return the output of the response
        return new Answer(chatResponse(question.getQuestion()).getResult().getOutput().getText());
    }

    public ChatResponse chatResponse(String project) {
        ChatResponse response = chatClient.prompt() // opening prompt
                .user(project) //submitting  prompt
                .call() //confirming prompt
                .chatResponse(); // prompt response

        return response;
    }
}
