package com.daebecodin.taskgenerator;

import com.daebecodin.model.Answer;
import com.daebecodin.model.Question;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskGeneratorController {

    private final TaskGeneratorService taskGeneratorService;

    public TaskGeneratorController(TaskGeneratorService taskGeneratorService) {
        this.taskGeneratorService = taskGeneratorService;
    }

    @GetMapping(value = "generate-tasks")
    public Answer generateTasks(
            @RequestParam(
                    value = "prompt",
                    defaultValue = "i start learning python but its overwhelming. Break down learning python into actionable steps. End goal is to do machine learning")
            String question
    ) {
        return taskGeneratorService.generateTasks(new Question(question));
    }
}
