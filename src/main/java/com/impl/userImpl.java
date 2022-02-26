package com.impl;



import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dao.UserDao;

import com.entity.User;

@Transactional
public class userImpl implements UserDao{

	
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Override
	public boolean check(String name, String pass) {
		Session session = this.sessionFactory.getCurrentSession();
		
		String hql = "select u.id from User u left join u.staff as s where s.id=:name and u.pass=:pass";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		query.setParameter("pass", pass);
		
		List result = query.list();
		
		if(result.size() > 0){
			
			return true;
		}
		else{
		return false;
				}
	}
	@Override
	public List<String> getRole(String name){
	
        Session session = this.sessionFactory.getCurrentSession();
		
		String hql = "select r.name from User u  left join u.staff t left join u.role r  where  t.code=:name ";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);

		List<String> result = (List<String>)query.list();
		
	
		return result;
	}

	@Override
	public User getUserByName(String name){
        Session session = this.sessionFactory.getCurrentSession();
        int id = getUserId(name);
		String hql = "from User u  where  u.id=:id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<User> l = (List<User>) query.list();
		User us = new User();
		if(l.size()>0){
			us = l.get(0);
		}
	
		
	
		return us;
	}
	@Override
	public User getUserById(Integer id){
        Session session = this.sessionFactory.getCurrentSession();
       
		String hql = "from User u where  u.id=:id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<User> l = (List<User>) query.list();
		User us = new User();
		if(l.size()>0){
			us = l.get(0);
		}
	
		
		return us;
	}
public int getUserId(String name){
		
        Session session = this.sessionFactory.getCurrentSession();
		
		String hql = "select u.id from User u left join u.staff t where  t.code=:name ";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);

		List<Integer> l = (List<Integer>) query.list();
		int result = -1;
		if(l.size()>0){
			result = l.get(0);
		}
		return result;
	}

	@Override
	public List<User> getList() {
		Session session = this.sessionFactory.getCurrentSession();
		String hql =  "from User   ";
		Query query = session.createQuery(hql);
		return (List<User>) query.list();
	}
	@Override
	public List<User> getListByRole(Integer role) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = " from User u where u.role.id = :role ";
		Query query = session.createQuery(hql);
		query.setParameter("role", role);
		return (List<User>) query.list();
	}
	@Override
	public void insert(User user){
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

	@Override
	public void update(User user){
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);
	}
	@Override
	public void delete(int id){
		Session session = this.sessionFactory.getCurrentSession();
		session.createQuery("delete User where id="+id+"").executeUpdate();
	}

}