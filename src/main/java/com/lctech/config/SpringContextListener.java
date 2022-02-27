package com.lctech.config;



import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import com.dao.CloseTimeDao;
import com.dao.WeightElectricQueueDao;
import com.entity.CloseTime;
import com.lctech.scheduler.ScheduledTasks;
import com.lctech.service.CloseTimeService;
import com.lctech.service.CloseWorkOrder;




@Component
public class SpringContextListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private ApplicationContext appContext;
	@Autowired
	private CloseTimeService cts;
	 @Autowired
	 private SimpMessagingTemplate template;
	 @Autowired
	 private  CloseWorkOrder closewo;
	 @Autowired
	 private  CloseTimeDao ctdao;
	@Autowired
	private WeightElectricQueueDao weqdao;
	 /* @Bean
	    public TaskScheduler taskScheduler() {
	        return new ConcurrentTaskScheduler();
	    }*/
	
	//@Autowired
	//private TaskScheduler cts;
	
	/**
	 * This method will run on application startup and load up the default book
	 * collection into the MongoDB database.
	 */
	public void onApplicationEvent(ContextRefreshedEvent event) {
		 //Thread t = new Thread(new CreateThreadRunnableExample(), "My Thread");
		//ApplicationContext ctx = SpringApplication.run(Application.class);

      //  ScheduledTasks scheduledTasks = new ScheduledTasks();
		if (event.getApplicationContext().equals(appContext)) {
			ScheduledTasks scheduledTasks = appContext.getBean(ScheduledTasks.class);
	        
	        List<String> lcron = cts.getCron();
	        scheduledTasks.scheduleAllCrons(lcron);
	        
//	        CloseTime lntc = ctdao.getNextTime(new Time(Calendar.getInstance().getTime().getTime()));
//			closewo.closeworkorder(lntc);
	        
		}
	

       
	};
}
