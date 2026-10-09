package com.application.taskmanager.application;

import com.application.taskmanager.application.output.TaskOutput;
import com.application.taskmanager.domain.TaskId;
import com.application.taskmanager.domain.TaskNotFoundException;
import com.application.taskmanager.domain.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTaskByIdUseCase {
    private final TaskRepository repository;

    public TaskOutput execute(TaskId id) {
        return repository.findById(id).map(TaskOutput::from)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}
