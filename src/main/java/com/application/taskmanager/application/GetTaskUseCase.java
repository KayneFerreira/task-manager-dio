package com.application.taskmanager.application;

import com.application.taskmanager.application.output.TaskOutput;
import com.application.taskmanager.domain.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTaskUseCase {
    private final TaskRepository repository;

    public List<TaskOutput> execute() {
        return repository.findAll()
                .stream()
                .map(TaskOutput::from)
                .toList();
    }
}
