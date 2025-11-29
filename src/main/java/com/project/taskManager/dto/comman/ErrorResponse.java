package com.project.taskManager.dto.comman;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ErrorResponse {
    private String message;
    private String errorCode;
    private Instant timestamp;
    private String path;
}
