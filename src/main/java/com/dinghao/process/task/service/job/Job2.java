package com.dinghao.process.task.service.job;

import com.dinghao.util.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * 测试job任务
 */
public class Job2 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Job2 doing something..."+ DateUtil.getDateTime(new Date()));
    }
}


