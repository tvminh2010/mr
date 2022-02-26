package com.impl;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.FGDao;
import com.entity.FG;





@Transactional
@Repository
public class FgImpl implements FGDao {

	 
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	
	public List<FG> getList() {
		Session session = this.sessionFactory.getCurrentSession();
		List<FG> list = (List<FG>) session.createQuery("from FG ").list();
		return list;
		
	}
	
	public Boolean  insert(FG fg) {
		Session session = this.sessionFactory.getCurrentSession();
         String customercode = findbyvtepartcode(fg.getVtepartname(),fg.getCustomer());
         if(customercode == null){
        	 customercode = findbyfgcode(fg.getFgcode(),fg.getCustomer());
        	 
         }
         if(customercode==null){
        	 session.save(fg);
        	 return true;
         }else{
        	 return false;
         }
		
		
		
	}
	
	public void del( FG fg) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(fg);
		
		
	}

	public void update( FG fg) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(fg);
		
		
	}
	
	public String findbyvtepartcode(String vtepartcode,String customer) {
		Session session = this.sessionFactory.getCurrentSession();
		List<FG> list = (List<FG>) session.createQuery("from FG where vtepartname like '"+ vtepartcode+ "' and upper(customer) like '"+ customer+"'" ).list();
		if(list.size()>0){
			return list.get(0).getCustomercode();
		}else{
			return null;
		}
		
	}
	
	public String findbyfgcode(String fgcode,String customer) {
		Session session = this.sessionFactory.getCurrentSession();
		List<FG> list = (List<FG>) session.createQuery("from FG where fgcode like '"+ fgcode+ "' and upper(customer) like '"+ customer+"'" ).list();
		if(list.size()>0){
			return list.get(0).getCustomercode();
		}else{
			return null;
		}
		
	}
	
	public FG findbyid(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		List<FG> list = (List<FG>) session.createQuery("from FG where id like "+ id ).list();
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
		
	}
}
