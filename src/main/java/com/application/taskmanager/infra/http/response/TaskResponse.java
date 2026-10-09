package com.application.taskmanager.infra.http.response;

import com.application.taskmanager.application.output.TaskOutput;
import com.application.taskmanager.domain.TaskStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
public record TaskResponse(String id, String title, String description, TaskStatus status) {
    public static TaskResponse from(TaskOutput output) {
        return new TaskResponse(output.id(),
                output.title(),
                output.description().orElse(null),
                output.status());
    }
}
