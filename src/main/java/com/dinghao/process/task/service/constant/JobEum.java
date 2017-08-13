package com.dinghao.process.task.service.constant;

import com.dinghao.process.task.service.job.HelloJob;
import com.dinghao.process.task.service.job.Job1;
import com.dinghao.process.task.service.job.Job2;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务容器
 */
public enum JobEum {

    HELLO_JOB("hello_job", "测试job", HelloJob.class),
    JOB1("job1", "测试job1", Job1.class),
    JOB2("job2", "测试job2", Job2.class);

    private String jobName;
    private String text;
    private Class cls;

    JobEum(String jobName, String text, Class cls) {
        this.jobName = jobName;
        this.text = text;
        this.cls = cls;
    }

    public static List<JobEum> getAll() {

        List<JobEum> list = new ArrayList<JobEum>();
        for (JobEum e : JobEum.values()) {
            list.add(e);
        }
        return list;
    }

    /**
     * 根据name获取
     *
     * @param jobName
     * @return
     */
    public static JobEum find(String jobName) {
        for (JobEum e : JobEum.values()) {

            if (e.getJobName().equals(jobName)) {
                return e;
            }
        }
        return null;

    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }
}
