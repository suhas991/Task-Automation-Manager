package com.project.taskManager.scheduler;

import com.project.taskManager.entity.Task;
import com.project.taskManager.executor.TaskExecutor;
import com.project.taskManager.repository.TaskRepository;
import com.project.taskManager.utils.CronUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class TaskExecutionScheduler {
    @Autowired
    private  TaskRepository taskRepository;
    @Autowired
    private TaskExecutor taskExecutor;

    @Scheduled(fixedDelay = 5000)
    public void runDueTask(){
        List<Task> tasks = taskRepository.findAll();

        Instant now = Instant.now();

        tasks.stream().filter(task -> CronUtils.isDue(task.getSchedule(),task.getLastRunAt(),now))
                .forEach(taskExecutor::executeTask);
    }
}
