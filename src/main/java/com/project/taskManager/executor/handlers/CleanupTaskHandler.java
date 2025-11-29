package com.project.taskManager.executor.handlers;

import com.project.taskManager.entity.Task;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CleanupTaskHandler {
    public void handle(Task task){

        JSONObject json = new JSONObject(task.getPayload());
        String url = json.getString("url");
        String method = json.getString("method");

        //actual logic

        System.out.println("calling " + method + " url "+url);
    }
}
