package com.project.taskManager.entity;

import com.project.taskManager.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_execution_logs")
public class TaskExecutionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long taskId;

    private Instant startedAt;
    private Instant finishedAt;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Lob
    private String output;

    @Lob
    private String error;
}
