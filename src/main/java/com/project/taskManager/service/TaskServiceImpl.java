package com.project.taskManager.service;

import com.project.taskManager.converter.TaskConverter;
import com.project.taskManager.dto.request.TaskRequest;
import com.project.taskManager.dto.response.TaskResponse;
import com.project.taskManager.entity.Task;
import com.project.taskManager.exception.TaskNotFoundException;
import com.project.taskManager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{

    @Autowired
    private final TaskRepository taskRepository;

    @Override
    public TaskResponse createTask(TaskRequest request) {
        Task task = TaskConverter.toEntity(request);
        Task saved = taskRepository.save(task);
        return TaskConverter.toResponse(saved);
    }

    @Override
    public TaskResponse getTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));
        return TaskConverter.toResponse(task);
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(TaskConverter::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new TaskNotFoundException(id));
        task.setName(request.getName());
        task.setType(request.getType());
        task.setSchedule(request.getSchedule());
        task.setPayload(request.getPayload());
        task.setMaxRetries(request.getMaxRetries());
        task.setUpdatedAt(Instant.now());

        Task saved = taskRepository.save(task);
        return TaskConverter.toResponse(saved);
    }

    @Override
    public void deleteTask(Long id) {
        if(!taskRepository.existsById(id)){
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }
}
