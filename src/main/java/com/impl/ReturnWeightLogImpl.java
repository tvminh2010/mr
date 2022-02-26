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
import com.dao.ReturnWeightLogDao;
import com.entity.CloseTime;
import com.entity.PsProduct;
import com.entity.ReturnWeightLog;
import com.config.Config;
import com.lctech.scheduler.CronConfig;
import com.lctech.scheduler.MyTask;
import com.lctech.scheduler.ScheduledTasks;
import com.lctech.service.CloseTimeService;



@Transactional
public class ReturnWeightLogImpl implements ReturnWeightLogDao{
	private static final Logger logger = Logger.getLogger(ReturnWeightLogImpl.class);
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
	public void save(ReturnWeightLog ct) {
		logger.info("save dao ReturnWeightLog" + ct.toString());
		Session session = this.sessionFactory.getCurrentSession();
		session.save(ct);
		
	}
	@Override
	public Object[] getLotno(String woid, String model) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select lotno,serialnew from ReturnWeightLog where  wo =:wo and model = :model ");
		query.setParameter("wo", woid);
		query.setParameter("model", model);
		List<Object[]> listps = (List<Object[]>) query.list();
	    if(listps !=null && listps.size()>0)
	    	return listps.get(0);
	    return null;
		
		
	}
	@Override
	public List<ReturnWeightLog> getReturnWeightLogBywoid(String woid) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ReturnWeightLog where  model is not null and model not like  '' and status= 1 and  wo =:woid order by serialnew");
		query.setParameter("woid", woid);

		List<ReturnWeightLog> listps = (List<ReturnWeightLog>) query.list();
	   
	    return listps;
	}
	@Override
	public List<ReturnWeightLog> getList(Integer page,String woname, String item,String serialno) {
		Session session = this.sessionFactory.getCurrentSession();
		
		String hql = " from ReturnWeightLog where 1= 1  ";
		if(woname !=null && woname != "") hql += " and woname like '%" + woname +"%'" ;
		if(item !=null && item != "") hql += " and model like '%" + item + "%'" ;
		if(serialno !=null && serialno !="") hql += " and serialnew like '%" + serialno  + "%'";
		hql += "  order by status desc,createdate desc, serialnew";
		Query query = session.createQuery(hql).setMaxResults(config.getPageSize())
	    		.setFirstResult((page - 1) * config.getPageSize());;

		List<ReturnWeightLog> listps = (List<ReturnWeightLog>) query.list();
	   
	    return listps;
	}





	@Override
	public List<Object[][]> getReturnWeightLogBywoidOrderbyNVL(String woid) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select wo,model,sum(qty),count(*) from ReturnWeightLog where model is not null and model not like  '' and  status=1 and  wo =:woid group by wo,model");
		query.setParameter("woid", woid);

		List<Object[][]> listps = query.list();
	   
	    return listps;
	}
	@Override
	public void updateStatus(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("update  ReturnWeightLog set status = 0 where id=:id");
		query.setParameter("id", id);

		query.executeUpdate();
	   
	   
		
	}
	@Override
	public Long countAll(String woname, String item,String serialno) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select count(*) from ReturnWeightLog where 1= 1  ";
		if(woname !=null && woname != "") hql += " and woname like '%" + woname +"%'" ;
		if(item !=null && item != "") hql += " and model like '%" + item + "%'" ;
		if(serialno !=null && serialno !="") hql += " and serialnew like '%" + serialno  + "%'";
		Query query = session.createQuery(hql);
	

          Long listps = (Long)query.uniqueResult();
	   
	    return listps;
	}
	@Override
	public ReturnWeightLog getReturnWeightLog(Long id) {
		ReturnWeightLog re  = new ReturnWeightLog();
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ReturnWeightLog where id= :id");
		query.setParameter("id", id);

		ReturnWeightLog listps = (ReturnWeightLog)query.uniqueResult();
	   
	    return listps;
		
	}
	@Override
	public void delete(Long id) {
		ReturnWeightLog re  = new ReturnWeightLog();
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete ReturnWeightLog where id= :id");
		query.setParameter("id", id);

		query.executeUpdate();
		
	}

}
