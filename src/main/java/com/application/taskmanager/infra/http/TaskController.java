package com.application.taskmanager.infra.http;

import com.application.taskmanager.application.*;
import com.application.taskmanager.domain.TaskId;
import com.application.taskmanager.infra.http.request.CreateTaskRequest;
import com.application.taskmanager.infra.http.request.UpdateTaskRequest;
import com.application.taskmanager.infra.http.response.TaskResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final GetTaskUseCase getTaskUseCase;
    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;

    @PostMapping
    TaskResponse create(@RequestBody @Valid CreateTaskRequest request) {
        var input = request.toInput();
        var output = createTaskUseCase.execute(input);
        return TaskResponse.from(output);
    }

    @GetMapping
    List<TaskResponse> list() {
        return getTaskUseCase.execute()
                .stream()
                .map(TaskResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    TaskResponse read(@PathVariable UUID id) {
        var output = getTaskByIdUseCase.execute(new TaskId(id));
        return TaskResponse.from(output);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id) {
        deleteTaskUseCase.execute(new TaskId(id));
    }

    @PatchMapping("/{id}")
    TaskResponse update(@PathVariable UUID id, @RequestBody UpdateTaskRequest request) {
        var input = request.toInput();
        var output = updateTaskUseCase.execute(input, new TaskId(id));
        return TaskResponse.from(output);
    }

}
