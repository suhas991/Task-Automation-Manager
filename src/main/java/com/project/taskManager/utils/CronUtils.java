package com.project.taskManager.utils;

import org.springframework.scheduling.support.CronExpression;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CronUtils {
    public static Boolean isDue(String cronExpression, Instant lastRunAt,Instant now){

        CronExpression cron = CronExpression.parse(cronExpression);

        LocalDateTime last = lastRunAt==null
                ? LocalDateTime.now().minusMinutes(1)
                : LocalDateTime.ofInstant(lastRunAt, ZoneId.systemDefault());

        LocalDateTime nextRun = cron.next(last);

        if (nextRun == null) return false;

        return !nextRun.isAfter(LocalDateTime.ofInstant(now,ZoneId.systemDefault()));
    }
}
