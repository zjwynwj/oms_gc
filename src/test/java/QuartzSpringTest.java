import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.servlet.http.HttpServletRequest;

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

            HttpServletRequest request;
            ApplicationContext application = new ClassPathXmlApplicationContext("applicationContext.xml");
            // Grab the Scheduler instance from the Factory
            SchedulerFactoryBean factoryBean = (SchedulerFactoryBean) application.getBean("quartzScheduler");
            Scheduler scheduler = factoryBean.getScheduler();
            System.out.println("000");

            // and start it off
            scheduler.start();

            doSomthing(scheduler);
            System.out.println("111");
            Thread.sleep(60000);
            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    private static void doSomthing(Scheduler scheduler) throws Exception {

        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(40)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);
    }


}
