package com.project.taskManager.service;

import com.project.taskManager.dto.request.TaskRequest;
import com.project.taskManager.dto.response.TaskResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest request);

    TaskResponse getTask(Long id);

    List<TaskResponse> getAllTasks();

    TaskResponse updateTask(Long id,TaskRequest request);

    void deleteTask(Long id);
}
