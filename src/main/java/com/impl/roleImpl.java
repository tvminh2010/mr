package com.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dao.RoleDao;
import com.entity.PartRole;
import com.entity.Role;
import com.entity.SubRole;


@Transactional
public class roleImpl implements RoleDao{
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	

	@Override
	public Role getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query =  session.createQuery("from Role where id = :id");
		query.setParameter("id", id);
		List<Role> list = (List<Role>) query.list();
		Role r= new Role();
		if(list.size()>0){
			r = list.get(0);
		}
		return r;
	}
	@Override
	public Role getByRole(int pr) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query =  session.createQuery("from Role r  where r.id= :pr");
		query.setParameter("pr", pr);
		List<Role> list = (List<Role>) query.list();
		
		Role r= new Role();
		if(list.size()>0){
			r = list.get(0);
		}
		return r;
	}
	@Override
	public List<Role> getList() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Role> list = (List<Role>) session.createQuery("from Role").list();
		return list;
	}
	
}
