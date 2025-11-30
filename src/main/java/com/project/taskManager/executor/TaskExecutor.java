package com.project.taskManager.executor;

import com.project.taskManager.entity.Task;
import com.project.taskManager.entity.TaskExecutionLog;
import com.project.taskManager.enums.TaskStatus;
import com.project.taskManager.executor.handlers.ApiCallTaskHandler;
import com.project.taskManager.executor.handlers.CleanupTaskHandler;
import com.project.taskManager.repository.TaskExecutionLogRepository;
import com.project.taskManager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TaskExecutor {

    @Autowired
    private ApiCallTaskHandler apiCallTaskHandler;

    @Autowired
    private CleanupTaskHandler cleanupTaskHandler;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskExecutionLogRepository taskExecutionLogRepository;

    public void executeTask(Task task){

        TaskExecutionLog log = new TaskExecutionLog();

        log.setTaskId(task.getId());
        log.setStartedAt(Instant.now());
        log.setStatus(TaskStatus.RUNNING);

        taskExecutionLogRepository.save(log);

        task.setStatus(TaskStatus.RUNNING);
        task.setRetryCount(task.getRetryCount()+1);
        taskRepository.save(task);

        try {
            switch (task.getType()){
                case API_CALL -> apiCallTaskHandler.handle(task);
                case CLEANUP -> cleanupTaskHandler.handle(task);
            }
            task.setStatus(TaskStatus.SUCCESS);
            log.setStatus(TaskStatus.SUCCESS);
            log.setOutput("Execution completed successfully");

        }catch (Exception e){
            task.setStatus(TaskStatus.FAILED);
            log.setStatus(TaskStatus.FAILED);
            log.setError(e.getMessage());
        }
        log.setFinishedAt(Instant.now());
        taskExecutionLogRepository.save(log);

        task.setLastRunAt(Instant.now());
        if (task.getStatus() == TaskStatus.SUCCESS) {
            task.setRetryCount(0);
        }
        taskRepository.save(task);
    }

}
