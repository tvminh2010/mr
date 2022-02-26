package com.impl;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.config.Config;
import com.dao.CoreWeightDao;
import com.entity.CoreWeight;
import com.entity.LineNo;
@Transactional
public class CoreWeightImpl implements CoreWeightDao{
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
	public void save(CoreWeight t) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(t);
		
	}

	@Override
	public CoreWeight getByid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CoreWeight getByItemcode(String itemcode) {
		 Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from CoreWeight where  pt_part =:itemcode ");
			query.setParameter("itemcode", itemcode);
			List<CoreWeight> list = (List<CoreWeight>) query.list();//get line p1 p2 p3 sample
			CoreWeight ln =new CoreWeight();
			if(list.size()>0)
			{
				 ln = list.get(0);
			}
			
			return ln;
	}
	@Override
	public List<CoreWeight> getListAll() {
		 Session session = this.sessionFactory.getCurrentSession();
		 
			Query query = session.createQuery("from CoreWeight  ");
		
			List<CoreWeight> list = (List<CoreWeight>) query.list();//get line p1 p2 p3 sample
		
			
			
			
			return list;
	}
	@Override
	public void deleteAll() {
		 Session session = this.sessionFactory.getCurrentSession();
		 
			Query query = session.createQuery("delete CoreWeight  ");
		
			query.executeUpdate();//get line p1 p2 p3 sample
		
			
			
			
			
		
	}
	@Override
	public List<Object[]> getTypeCore() {
		 Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("select distinct typecore, coreweight from CoreWeight  ");
	
			List<Object[]> list =query.list();//get line p1 p2 p3 sample
		
			
			return list;
	}

}
