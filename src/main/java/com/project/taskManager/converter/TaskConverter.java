package com.project.taskManager.converter;

import com.project.taskManager.dto.request.TaskRequest;
import com.project.taskManager.dto.response.TaskResponse;
import com.project.taskManager.entity.Task;
import com.project.taskManager.enums.TaskStatus;

import java.time.Instant;

public class TaskConverter {

    public static Task toEntity(TaskRequest req) {
        return Task.builder()
                .name(req.getName())
                .type(req.getType())
                .schedule(req.getSchedule())
                .payload(req.getPayload())
                .maxRetries(req.getMaxRetries() != null ? req.getMaxRetries() : 3)
                .retryCount(0)
                .status(TaskStatus.PENDING)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public static TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .name(task.getName())
                .type(task.getType())
                .schedule(task.getSchedule())
                .status(task.getStatus())
                .payload(task.getPayload())
                .maxRetries(task.getMaxRetries())
                .retryCount(task.getRetryCount())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }
}