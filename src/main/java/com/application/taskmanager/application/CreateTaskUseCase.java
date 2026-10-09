package com.application.taskmanager.application;

import com.application.taskmanager.application.input.CreateTaskInput;
import com.application.taskmanager.application.output.TaskOutput;
import com.application.taskmanager.domain.Task;
import com.application.taskmanager.domain.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTaskUseCase {
    private final TaskRepository repository;

    public TaskOutput execute(CreateTaskInput input) {
        var task = new Task(input.title(), input.description());
        var saved = repository.save(task);
        return TaskOutput.from(saved);
    }
}
