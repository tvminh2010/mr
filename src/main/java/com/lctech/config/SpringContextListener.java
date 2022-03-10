package com.lctech.config;



import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import com.config.DBPosgressConnection;
import com.dao.CloseTimeDao;
import com.dao.ReturnWeightLogDao;
import com.dao.WeightElectricQueueDao;
import com.dao.WorkOrderDao;
import com.entity.CloseTime;
import com.entity.CoreWeight;
import com.entity.Product;
import com.entity.ReturnWeightLog;
import com.entity.WorkOrder;
import com.lctech.scheduler.MyTask;
import com.lctech.scheduler.ScheduledTasks;
import com.lctech.service.CloseTimeService;
import com.lctech.service.ExportExcel;




@Component
public class SpringContextListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = Logger.getLogger(SpringContextListener.class);

	@Autowired
	private ApplicationContext appContext;
	@Autowired
	private CloseTimeService cts;
	 @Autowired
	 private  CloseTimeDao ctdao;
	 @Autowired
	 private ExportExcel  eportexcel;
	@Autowired
	private WeightElectricQueueDao weqdao;
	@Autowired
	private WorkOrderDao wodao;
	  @Autowired
	   private  ReturnWeightLogDao rwldao;
		 @Autowired
			private DBPosgressConnection posgressConn;
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
	        
		}
	
		
		//update coreweightlog
		
		 Date d = addDays(new Date(),-40);
		 List<ReturnWeightLog> tt = rwldao.getByDate(d) ;

		 for(ReturnWeightLog r : tt) {
			 
		 
			 HashMap<String,String> item = posgressConn.getItemCode(r.getSerialold());
				if(item != null && item.size()>1) {
			     String itemcode  = item.get("masp");
				
				
			
				
				
				SimpleDateFormat formatinput = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat formatoutput = new SimpleDateFormat("dd/MM/yyyy");
				Date inputd;
				try {
					inputd = formatinput.parse(item.get("receivingdate"));
					logger.info("receivingdate:" + inputd);
					logger.info("receivingdate ouput :" + formatoutput.format(inputd));

					 r.setReceivingdate(formatoutput.format(inputd));
					 
					 rwldao.save(r);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				
		 }
		 }
		//export return
		  List<WorkOrder> lwo = wodao.getListbyDate(d);
		  for(WorkOrder wo: lwo) {
		       eportexcel.exportReturn(wo,null);
		    
		  }
       
	};
	  public static Date addDays(Date date, int days)
	    {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, days); //minus number would decrement the days
	        return cal.getTime();
	    }
}
