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
import com.entity.CloseTime;
import com.config.Config;
import com.lctech.scheduler.CronConfig;
import com.lctech.scheduler.MyTask;
import com.lctech.scheduler.ScheduledTasks;
import com.lctech.service.CloseTimeService;



@Transactional
public class CloseTimeImpl implements CloseTimeDao{
	private static final Logger logger = Logger.getLogger(CloseTimeImpl.class);
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
	public void save(CloseTime ct) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(ct);
		resetScheduler();
		
	}
	private void resetScheduler(){
		List<String> lcron = cts.getCron();
	  	
	  	taskScheduler.scheduleAllCrons(lcron);
	}
	@Override
	public List<CloseTime> getList() {
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("from CloseTime order by closetime ");
	
	
		List<CloseTime> list = (List<CloseTime>) query.list();
		
		return list;
	}
	

	
	@Override
	public CloseTime getNextTime(Time d) {
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("from CloseTime ct "
				+" where DATEDIFF(N,'"+d+"' ,closetime)>0 order by DATEDIFF(N,'"+d+"' ,closetime) asc  ");
		List<CloseTime> list = (List<CloseTime>) query.list();
		if(list.isEmpty()){
			Query query1 = session.createQuery("from CloseTime ct order by ct.closetime asc ");
			CloseTime nexttime = (CloseTime) query1.setMaxResults(1).list().get(0);
			return nexttime;
		}else{
			return list.get(0);
		}
	
	}

	@Override
	public CloseTime getCurrentTime(Time d) {
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("from CloseTime ct "
				+" where DATEDIFF(N,'"+d+"' ,closetime)<=0 order by DATEDIFF(N,'"+d+"',closetime) desc  ");
		
		logger.info(query);
		List<CloseTime> list = (List<CloseTime>) query.list();
		if(list.isEmpty()){
			Query query1 = session.createQuery("from CloseTime ct order by ct.closetime desc ");
			CloseTime nexttime = (CloseTime) query1.setMaxResults(1).list().get(0);
			return nexttime;
		}else{
			return list.get(0);
		}
	}
	@Override
	public CloseTime getNextTime() {
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("from CloseTime ct "
				+" where DATEDIFF(N,CONVERT(VARCHAR(5),getdate(),108) ,closetime)>0 order by DATEDIFF(N,CONVERT(VARCHAR(5),getdate(),108) ,closetime)   ");
		List<CloseTime> list = (List<CloseTime>) query.list();
		if(list.isEmpty()){
			Query query1 = session.createQuery("from CloseTime ct order by ct.closetime asc ");
			CloseTime nexttime = (CloseTime) query1.setMaxResults(1).list().get(0);
			return nexttime;
		}else{
			return list.get(0);
		}
	
	}
	@Override
	public CloseTime getById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("from CloseTime where id = :id ");
	
		query.setParameter("id", id);
		CloseTime list = (CloseTime) query.list().get(0);
		
		return list;
	}
	@Override
	public void delete(CloseTime ct) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(ct);
		resetScheduler();
		
	}
	@Override
	public void deletelist(String lct) {
		Session session = this.sessionFactory.getCurrentSession();

		Query query = session.createQuery("delete  CloseTime where id in(" +lct+ ")");
	
		//query.setParameter("lct", lct);
		query.executeUpdate();
		resetScheduler();
		
		
	}
	@Override
	public CloseTime getCurrentTime() {
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("from CloseTime ct "
				+" where DATEDIFF(N,CONVERT(VARCHAR(5),getdate(),108) ,closetime)<=0 order by DATEDIFF(N,CONVERT(VARCHAR(5),getdate(),108) ,closetime) desc  ");
		List<CloseTime> list = (List<CloseTime>) query.list();
		if(list.isEmpty()){
			Query query1 = session.createQuery("from CloseTime ct order by ct.closetime desc ");
			CloseTime nexttime = (CloseTime) query1.setMaxResults(1).list().get(0);
			return nexttime;
		}else{
			return list.get(0);
		}
	}
	@Override
	public Boolean checkDouble(CloseTime lct) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		Query query = session.createQuery("from CloseTime ct "
				+" where ct.id <> :ctid and  (ct.name = :name or DATEDIFF(N,CONVERT(VARCHAR(5),:closetime,108) ,ct.closetime)=0) ");
		query.setParameter("ctid", lct.getId()!=null?lct.getId():-1);
		query.setParameter("name", lct.getName());
		query.setParameter("closetime", lct.getClosetime());
		List<CloseTime> list = (List<CloseTime>) query.list();
		if(list!= null && !list.isEmpty() && list.size()>0 ){
		
			return true;
		}else{
			return false;
		}
		
	}

}
