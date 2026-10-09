package com.application.taskmanager.infra.http.request;

import com.application.taskmanager.application.input.UpdateTaskInput;
import com.application.taskmanager.domain.TaskStatus;

import java.util.Optional;

public record UpdateTaskRequest(Optional<String> title, Optional<String> description, Optional<String> status) {

    public UpdateTaskInput toInput() {
        return new UpdateTaskInput(title, description, status.map(TaskStatus::valueOf));
    }
}
