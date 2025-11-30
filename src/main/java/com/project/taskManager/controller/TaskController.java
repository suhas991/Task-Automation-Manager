package com.project.taskManager.controller;

import com.project.taskManager.dto.request.TaskRequest;
import com.project.taskManager.dto.response.TaskResponse;
import com.project.taskManager.entity.TaskExecutionLog;
import com.project.taskManager.exception.TaskNotFoundException;
import com.project.taskManager.repository.TaskExecutionLogRepository;
import com.project.taskManager.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskExecutionLogRepository taskExecutionLogRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@Valid @RequestBody TaskRequest request){
        return taskService.createTask(request);
    }

    @GetMapping("/{id}")
    public TaskResponse getTask(@PathVariable Long id){
        return taskService.getTask(id);
    }

    @GetMapping
    public List<TaskResponse> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id , @Valid @RequestBody TaskRequest request){
        return taskService.updateTask(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    @GetMapping("/{id}/logs")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskExecutionLog> getAllLogsById(@PathVariable Long id){
        List<TaskExecutionLog> logs =
                taskExecutionLogRepository.findByTaskIdOrderByStartedAtDesc(id);
        return logs;
    }
}
