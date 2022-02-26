package com.lctech.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.controller.MainController;

@Component
public class ScheduledTasks {
	//@Autowired
	private TaskScheduler scheduler;

	@Autowired
	private CronConfig cronConfig;
	private static List<ScheduledFuture> scheduledFutures = new ArrayList<>();
	@Autowired
	private MyTask myTask;
	private static final Logger logger = Logger.getLogger(MainController.class);
	 @Bean
	 public TaskScheduler scheduler() {
	        if (scheduler == null) {
	            ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
	            scheduler.setPoolSize(10);
	            scheduler.afterPropertiesSet();
	        }
	        return scheduler;
	    }
	 
	 @Bean
	    public TaskScheduler taskScheduler() {
	        return new ConcurrentTaskScheduler();
	    }
	public void scheduleAllCrons(List<String> lcron) {

		/*
		 * for(String cron :cronConfig.getSchedules()){ //logger.info(cron);
		 * 
		 * taskScheduler.schedule(myTask, new CronTrigger(cron));
		 * 
		 * }
		 */
		 scheduler = taskScheduler();
		   if (scheduler == null) {
	            logger.error("Unable to schedule job as scheduler was not found");
	            return;
	        }
		 cancelAll();
		for (String cron : lcron) {
			logger.info(cron);

			//scheduler.schedule(myTask, new CronTrigger(cron));
			ScheduledFuture future = scheduler.schedule(myTask, new CronTrigger(cron));
		    scheduledFutures.add(future);
		}

	}
	  private void cancelAll() {
	        for (ScheduledFuture scheduledFuture : scheduledFutures) {
	            scheduledFuture.cancel(true);
	        }
	        scheduledFutures.clear();
	    }

}
