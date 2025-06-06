package com.daebecodin.taskgenerator;

import com.daebecodin.model.Answer;
import com.daebecodin.model.Question;

public interface TaskGenerator {
    public Answer generateTasks(Question question);
}
