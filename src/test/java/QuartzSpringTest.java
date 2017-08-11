import com.alibaba.fastjson.JSON;
import com.dinghao.process.task.service.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Set;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 最简单的测试案例
 *
 * @author nwj
 */
public class QuartzSpringTest {

    public static void main(String[] args) throws Exception {

        try {

            ApplicationContext application = new ClassPathXmlApplicationContext("applicationContext.xml");
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = (Scheduler) application.getBean("quartzScheduler");

            scheduler.start();
            System.out.println("0000");

            doSomthing(scheduler);
            System.out.println("1111111");
            Thread.sleep(60000);

            scheduler.shutdown();
           /* System.out.println("000");
            // and start it off
            scheduler.start();
            doSomthing(scheduler);
            getSchedulerJobInfo(scheduler);
            Thread.sleep(60000);
            System.out.println("111");
            scheduler.shutdown();*/

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    private static void doSomthing(Scheduler scheduler) throws Exception {

        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(HelloJob.class)
                .withIdentity("job12", "group12")
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger12", "group12")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);

        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
    }


    private static void getSchedulerJobInfo(Scheduler quartzScheduler) throws SchedulerException {
        List<String> triggerGroupNames = quartzScheduler.getTriggerGroupNames();
        System.out.println("triggerGroupNames=" + triggerGroupNames);
        for (String triggerGroupName : triggerGroupNames) {
            Set<TriggerKey> triggerKeySet = quartzScheduler
                    .getTriggerKeys(GroupMatcher
                            .triggerGroupEquals(triggerGroupName));
            for (TriggerKey triggerKey : triggerKeySet) {
                Trigger t = quartzScheduler.getTrigger(triggerKey);
                if (t instanceof CronTrigger) {
                    CronTrigger trigger = (CronTrigger) t;
                    JobKey jobKey = trigger.getJobKey();
                    JobDetail jd = quartzScheduler.getJobDetail(jobKey);
                    // jobInfo.setDuration(Long.parseLong(jd.getDescription()));
                    Trigger.TriggerState triggerState = quartzScheduler
                            .getTriggerState(trigger.getKey());
                    // NORMAL正常,
                    // PAUSED暂停,
                    // COMPLETE完全,
                    // ERROR错误,
                    // BLOCKED阻塞
                    JobDataMap map = quartzScheduler.getJobDetail(jobKey)
                            .getJobDataMap();


                    System.out.println(JSON.toJSON(jobKey));
                    System.out.println(JSON.toJSON(jd));
                    System.out.println(JSON.toJSON(triggerState));
                    System.out.println(JSON.toJSON(map));

                }
            }
        }
    }

}
