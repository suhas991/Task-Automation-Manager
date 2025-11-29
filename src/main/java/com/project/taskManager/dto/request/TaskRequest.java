package com.project.taskManager.dto.request;
import com.project.taskManager.enums.TaskType;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskRequest {

    @NotBlank
    private String name;

    @NotNull
    private TaskType type;

    private String schedule;

    private String payload;

    private Integer maxRetries;
}
