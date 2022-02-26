package com.impl;



import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;





import com.config.Config;
import com.dao.WeightElectricQueueDao;
import com.entity.ReceiptComp;
import com.entity.WeightElectricQueue;
import com.lctech.scheduler.CronConfig;




@Transactional
public class WeightElectricQueueImpl implements WeightElectricQueueDao{
	private static final Logger logger = Logger.getLogger(WeightElectricQueueImpl.class);
private SessionFactory sessionFactory;





	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}


	@Override
	public void save(WeightElectricQueue ct) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(ct);
		
	}
	@Override
	public void delete(WeightElectricQueue d) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(d);
		
	}
	@Override
	public List<WeightElectricQueue> getAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Query query =  session.createQuery("from WeightElectricQueue order by tracsactiondate desc ");
		List<WeightElectricQueue> list = (List<WeightElectricQueue>)query.list();
		return list;
	}
	@Override
	public void deleteAll() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query =  session.createQuery("delete WeightElectricQueue ");
		query.executeUpdate();

		
	}






	



}
