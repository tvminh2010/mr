package com.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;




import com.dao.ReturnWeightNumberDao;
import com.entity.CloseTime;
import com.entity.ReturnWeightNumber;
import com.config.Config;
import com.lctech.scheduler.CronConfig;
import com.lctech.scheduler.MyTask;
import com.lctech.scheduler.ScheduledTasks;
import com.lctech.service.CloseTimeService;



@Transactional
public class NumberCoreImpl implements ReturnWeightNumberDao{
	private static final Logger logger = Logger.getLogger(NumberCoreImpl.class);
private SessionFactory sessionFactory;
@Autowired
private ScheduledTasks taskScheduler;
@Autowired
private CronConfig cronConfig;

@Autowired
private MyTask myTask;
   @Autowired
   private Config config;
   @Autowired
	private CloseTimeService cts;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void save(ReturnWeightNumber ct) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(ct);
	
		
	}
	@Override
	public Integer getNumberbyDate(Date d) {
		Session session = this.sessionFactory.getCurrentSession();	
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String d1 = simpleDateFormat.format(d);
		Query query = session.createQuery("from ReturnWeightNumber where d = '"+ d1 +"' ");
	
	

         List<ReturnWeightNumber> lr =   query.list();
         if(lr!=null & lr.size()>0) {
        	 ReturnWeightNumber rwn = lr.get(0);
        	 rwn.setNumber(rwn.getNumber()+1);
        	 save(rwn);
        	 return lr.get(0).getNumber();
         }else {
        	 ReturnWeightNumber rwn = new ReturnWeightNumber();
        	 rwn.setD(d);
        	 rwn.setNumber(1);
        	 save(rwn);
        	 return 1;
         }
		
		
	}



	

}
