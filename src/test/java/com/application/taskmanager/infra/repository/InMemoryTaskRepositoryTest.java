package com.application.taskmanager.infra.repository;

import com.application.taskmanager.domain.TaskRepository;
import com.application.taskmanager.domain.TaskRepositoryTest;

class InMemoryTaskRepositoryTest extends TaskRepositoryTest {

    @Override
    protected TaskRepository createRepository() {
        return new InMemoryTaskRepository();
    }

}