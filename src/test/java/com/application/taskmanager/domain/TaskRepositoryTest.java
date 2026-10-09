package com.application.taskmanager.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class TaskRepositoryTest {

    TaskRepository repository;

    protected abstract TaskRepository createRepository();

    @BeforeEach
    public void setUp() {
        this.repository = createRepository();
    }

    @Test
    @DisplayName("Deve salvar e retornar a tarefa cadastrada")
    void save_ShouldPersistTask() {
        Task task = new Task("Estudar Java", Optional.of("Praticar testes"));

        Task savedTask = repository.save(task);

        assertThat(savedTask).isNotNull();
        assertThat(savedTask.getId()).isEqualTo(task.getId());
        assertThat(repository.findById(task.getId())).contains(task);
    }

    @Test
    @DisplayName("Deve retornar todas as tarefas salvas")
    void findAll_ShouldReturnAllTasks() {
        Task task1 = new Task("Tarefa 1", Optional.empty());
        Task task2 = new Task("Tarefa 2", Optional.empty());
        repository.save(task1);
        repository.save(task2);

        List<Task> tasks = repository.findAll();

        assertThat(tasks)
                .hasSize(2)
                .containsExactlyInAnyOrder(task1, task2);
    }

    @Test
    @DisplayName("Deve buscar uma tarefa por ID quando ela existir")
    void findById_WhenTaskExists_ShouldReturnTask() {
        Task task = new Task("Estudar Spring", Optional.empty());
        repository.save(task);

        Optional<Task> result = repository.findById(task.getId());

        assertThat(result).contains(task);
    }

    @Test
    @DisplayName("Deve retornar Optional vazio ao buscar por ID inexistente")
    void findById_WhenTaskDoesNotExist_ShouldReturnEmptyOptional() {
        TaskId nonExistentId = new TaskId();

        Optional<Task> result = repository.findById(nonExistentId);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Deve remover a tarefa com sucesso pelo ID")
    void delete_ShouldRemoveTask() {
        Task task = new Task("Tarefa para deletar", Optional.empty());
        repository.save(task);

        repository.delete(task.getId());

        assertThat(repository.findById(task.getId())).isEmpty();
    }

}