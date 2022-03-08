package com.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.controller.AdminController;
import com.dao.StaffDao;
import com.entity.Staff;

@Transactional
public class staffImpl implements StaffDao{
	private static final Logger logger = Logger.getLogger(staffImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Staff> getList() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Staff> list = (List<Staff>) session.createQuery("from Staff").list();
		return list;
	}

	@Override
	public List<Staff> getList(String part) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Staff where part like :part ";
		Query query = session.createQuery(hql);
		query.setParameter("part", part);
		List<Staff> list = (List<Staff>) query.list();
		return list;
	}
	@Override
	public Staff getByCode(String code) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Staff where code=:code ";
		Query query = session.createQuery(hql);
		query.setParameter("code", code);
		List<Staff> l = (List<Staff>) query.list();
		
		if(l.size()>0){
			 return l.get(0);
			}else{
				return null;
			}
	}
	@Override
	public Staff getByID(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Staff where id=:id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Staff> l = (List<Staff>) query.list();
		
		if(l.size()>0){
		 return l.get(0);
		}else{
			return null;
		}
		
	}
	@Override
	public void insert(Staff staff) {
		try {
			staff.setFirstName(new String(staff.getFirstName().getBytes("iso-8859-1"), "UTF-8"));
			staff.setLastName(new String(staff.getLastName().getBytes("iso-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.info(e);
			e.printStackTrace();
		}
		Session session = this.sessionFactory.getCurrentSession();
		session.save(staff);
	}

	@Override
	public void update(Staff staff) {
		try {
			staff.setFirstName(new String(staff.getFirstName().getBytes("iso-8859-1"), "UTF-8"));
			staff.setLastName(new String(staff.getLastName().getBytes("iso-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.info(e);
			e.printStackTrace();
		}
		Session session = this.sessionFactory.getCurrentSession();
		session.update(staff);
	}


	@Override
	public void delete(Staff st) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(st);
		}
	
	@Override
	public void delete(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		session.createQuery("delete Staff where id = '"+ id +"'").executeUpdate();
	}

	
	
}
