package com.lctech.scheduler;





import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;










import com.dao.CloseTimeDao;
import com.entity.CloseTime;
import com.entity.Greeting;
import com.lctech.service.CloseWorkOrder;

@Controller
@Component
public class MyTask  implements Runnable {
	private static final Logger logger = Logger.getLogger(MyTask.class);
	 @Autowired
	 private SimpMessagingTemplate template;
	 @Autowired
	 private  CloseWorkOrder closewo;
	 @Autowired
	 private  CloseTimeDao ctdao;
	@Override
	public void run() {
		logger.info("Task: "+ new Date());
		//CloseTime lnt = ctdao.getNextTime();
		CloseTime lntc = ctdao.getCurrentTime();
		closewo.closeworkorder(lntc);
	
		this.template.convertAndSend("/topic/greetings","");
		
	}
	
}
