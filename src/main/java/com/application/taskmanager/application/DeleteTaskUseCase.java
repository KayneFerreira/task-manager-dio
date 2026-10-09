package com.application.taskmanager.application;

import com.application.taskmanager.domain.TaskId;
import com.application.taskmanager.domain.TaskNotFoundException;
import com.application.taskmanager.domain.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteTaskUseCase {
    private final TaskRepository repository;

    public void execute(TaskId id) {
        if (repository.findById(id).isEmpty()) {
            throw new TaskNotFoundException(id);
        }
        repository.delete(id);
    }
}
