package com.project.taskManager.executor.handlers;

import com.project.taskManager.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.json.JSONObject;

import javax.sound.midi.Soundbank;

@Component
@RequiredArgsConstructor
public class ApiCallTaskHandler {

    public void handle(Task task){
        JSONObject json = new JSONObject(task.getPayload());
        String url= json.getString("url");
        String method = json.getString("method");

        // actual logic will write later
        System.out.println("calling " + method + " url "+url);
    }
}
