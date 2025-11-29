package com.project.taskManager.entity;

import com.project.taskManager.enums.TaskStatus;
import com.project.taskManager.enums.TaskType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TaskType type;

    private String schedule;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Lob
    private String payload;

    private Integer maxRetries;

    private Integer retryCount;

    private Instant createdAt;

    private  Instant lastRunAt;

}
