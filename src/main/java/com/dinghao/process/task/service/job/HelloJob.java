package com.dinghao.process.task.service.job;

import com.dinghao.util.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("job doing something..."+ DateUtil.getDateTime(new Date()));
    }
}


