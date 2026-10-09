package com.application.taskmanager.application.output;

import com.application.taskmanager.domain.Task;
import com.application.taskmanager.domain.TaskStatus;

import java.util.Optional;

public record TaskOutput(String id, String title, Optional<String> description, TaskStatus status) {

    public static TaskOutput from(Task task) {
        return new TaskOutput(task.getId().id().toString(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus());
    }

}
