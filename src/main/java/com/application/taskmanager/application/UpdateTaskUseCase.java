package com.application.taskmanager.application;

import com.application.taskmanager.application.input.UpdateTaskInput;
import com.application.taskmanager.application.output.TaskOutput;
import com.application.taskmanager.domain.TaskId;
import com.application.taskmanager.domain.TaskNotFoundException;
import com.application.taskmanager.domain.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateTaskUseCase {

    public final TaskRepository repository;

    public TaskOutput execute(UpdateTaskInput input, TaskId id) {
        var task = repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.update(input.title(), input.description(), input.status());
        var updated = repository.save(task);
        return TaskOutput.from(updated);
    }
}
