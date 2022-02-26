package com.impl;

import java.sql.Time;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;




import com.dao.CloseTimeDao;
import com.dao.ReturnExcelDao;
import com.entity.CloseTime;
import com.entity.CoreWeight;
import com.entity.ReturnExcel;
import com.config.Config;
import com.lctech.scheduler.CronConfig;
import com.lctech.scheduler.MyTask;
import com.lctech.scheduler.ScheduledTasks;
import com.lctech.service.CloseTimeService;



@Transactional
public class ReturnExcelImpl implements ReturnExcelDao{
	private static final Logger logger = Logger.getLogger(ReturnExcelImpl.class);
private SessionFactory sessionFactory;

@Autowired
private Config config;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}


	@Override
	public void save(ReturnExcel ct) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(ct);
	
		
	}
	@Override
	public List<ReturnExcel> getList(Integer page) {
		 Session session = this.sessionFactory.getCurrentSession();
		 
			Query query = session.createQuery("select re.id,re.woname,p.pt_desc1,re.line ,re.createdate,re.pathexcel from ReturnExcel re , Product p where  re.model = p.pt_part order by createdate desc").setMaxResults(config.getPageSize())
		    		.setFirstResult((page - 1) * config.getPageSize());
		
			List<ReturnExcel> list = (List<ReturnExcel>) query.list();//get line p1 p2 p3 sample
		
			
			
			
			return list;
	}
	@Override
	public Long getSize() {
		 Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("select count(*) from ReturnExcel  ");
			Long list = (Long) query.uniqueResult();//get line p1 p2 p3 sample
			return list;
	}





}
