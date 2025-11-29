package com.project.taskManager.executor;

import com.project.taskManager.entity.Task;
import com.project.taskManager.enums.TaskStatus;
import com.project.taskManager.executor.handlers.ApiCallTaskHandler;
import com.project.taskManager.executor.handlers.CleanupTaskHandler;
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

    public void executeTask(Task task){

        task.setStatus(TaskStatus.RUNNING);
        task.setRetryCount(task.getRetryCount()+1);
        taskRepository.save(task);

        try {
            switch (task.getType()){
                case API_CALL -> apiCallTaskHandler.handle(task);
                case CLEANUP -> cleanupTaskHandler.handle(task);
            }
            task.setStatus(TaskStatus.SUCCESS);

        }catch (Exception e){
            task.setStatus(TaskStatus.FAILED);
        }

        task.setLastRunAt(Instant.now());
        taskRepository.save(task);
    }

}
