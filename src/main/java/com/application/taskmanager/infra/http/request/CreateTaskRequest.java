package com.application.taskmanager.infra.http.request;

import com.application.taskmanager.application.input.CreateTaskInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record CreateTaskRequest(
        @NotBlank(message = "O campo 'Título' não pode ser vazio!")
        @Size(min = 3, max = 100, message = "Tamanho do título permitido: min=3, max=100")
        String title,
        Optional<String> description) {

    public CreateTaskInput toInput() {
        return new CreateTaskInput(title, description);
    }
}
