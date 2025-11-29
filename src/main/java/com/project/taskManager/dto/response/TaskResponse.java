package com.project.taskManager.dto.response;

import com.project.taskManager.entity.Task;
import com.project.taskManager.enums.TaskStatus;
import com.project.taskManager.enums.TaskType;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class TaskResponse {
    private Long id;
    private String name;
    private TaskType type;
    private TaskStatus status;
    private String schedule;
    private String payload;
    private Integer maxRetries;
    private Integer retryCount;
    private Instant createdAt;
    private Instant updatedAt;
}
