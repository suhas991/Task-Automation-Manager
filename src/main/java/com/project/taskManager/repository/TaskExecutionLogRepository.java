package com.project.taskManager.repository;

import com.project.taskManager.entity.TaskExecutionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskExecutionLogRepository extends JpaRepository<TaskExecutionLog,Long> {
    List<TaskExecutionLog> findByTaskIdOrderByStartedAtDesc(Long taskId);

}
