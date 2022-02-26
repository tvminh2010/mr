package com.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dao.LineNoDao;
import com.entity.LineNo;
@Transactional
public class lineNoImpl implements LineNoDao{

	
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}


	
	

	@Override
	public Integer getLineNoId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createLineNo(String name) {
		// TODO Auto-generated method stub
	}
	@Override
	public boolean checkExit(String lineno,Integer part) {
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("from LineNo where  part.id =:part and lineNo like :lineno ");
		query.setParameter("part", part);
		query.setParameter("lineno", lineno);
		List<LineNo> list = (List<LineNo>) query.list();//get line p1 p2 p3 sample
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
		
	}
	@Override
	public void insert(LineNo ln){
		Session session = this.sessionFactory.getCurrentSession();		
		session.save(ln);
		
	}	
	@Override
	public void delete(int id){
		   Session session = this.sessionFactory.getCurrentSession();
		
		   int result = session.createQuery("delete LineNo where id = "+id+"").executeUpdate();
		  
	   }
	@Override
	public void update(LineNo ln){
		Session session = this.sessionFactory.getCurrentSession();
		session.update(ln);
	}


	
	@Override
	public LineNo lineBYID(int id) {
     Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from LineNo where  id =:id ");
		query.setParameter("id", id);
		List<LineNo> list = (List<LineNo>) query.list();//get line p1 p2 p3 sample
		LineNo ln =new LineNo();
		if(list.size()>0)
		{
			 ln = list.get(0);
		}
		
		return ln;
	}

	@Override
	public LineNo getLineNoName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from LineNo where  name =:name ");
		query.setParameter("name", name);
		List<LineNo> list = (List<LineNo>) query.list();
		LineNo ln =new LineNo();
		if(list.size()>0)
		{
			 ln = list.get(0);
		}
		
		return ln;
	}





	@Override
	public List<LineNo> getlistLineNo() {
		
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("from LineNo ");
		
		List<LineNo> list = (List<LineNo>) query.list();//get line p1 p2 p3 sample
         return list;
	}

}
