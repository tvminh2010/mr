package com.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProductDao;
import com.config.Config;
import com.controller.AdminController;
import com.entity.Product;

@Transactional
public class productImpl implements ProductDao {
	private static final Logger logger = Logger.getLogger(productImpl.class);

	@Autowired
	private Config config;
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Override
	public List<Product> listProduct(){
		Session session = this.sessionFactory.getCurrentSession();
	   
		List<Product> list = (List<Product>) session.createQuery("from Product")
				.list();
		return list;
	}
	

	

	public Integer getProductId() {
		return null;
	}
	
	public void createProduct(String name) {
	}
	@Override
	public void insert(Product p){
		Session session = this.sessionFactory.getCurrentSession();		
		session.save(p);
		session.flush();
        session.clear();
	}
	@Override
	public void delete(String id){
		   Session session = this.sessionFactory.getCurrentSession();
		   int result = session.createQuery("delete Product where id = '"+id+"'").executeUpdate();
	   }
	@Override
	public void delete(Product model){
		   Session session = this.sessionFactory.getCurrentSession();
		   session.delete(model);
	   }

	@Override
	public Product getProductById(String id) {
		 Session session = this.sessionFactory.getCurrentSession();
		 String hql = "from Product where pt_part='"+id+"'";
		 Query query = session.createQuery(hql);
		 List<Product> product = query.list();
		 Product pro = new Product();
		 if(product.size()>0){
		  pro = product.get(0);
		    return pro;
		 }else{
			 return null;
		 }
		
	}
	@Override
	public Product getProductByDesc1(String desc1) {
		 Session session = this.sessionFactory.getCurrentSession();
		 String hql = "from Product where pt_desc1 like '"+desc1+"'";
		 Query query = session.createQuery(hql);
		 List<Product> product = query.list();
		 if(product.size()>0){
		 Product pro = product.get(0);
		 return pro;
		 }else{
			 return null;
		 }
		
	}
	@Override
	public Long getSize() {
		Session session = this.sessionFactory.getCurrentSession();
		 String countQ = "Select count (f.id) from Product f";
		    Query countQuery = session.createQuery(countQ);
		    Long countResults = (Long) countQuery.uniqueResult();
		    
		    return countResults;
	}
	@Override
	public void deleteAll() {
		Session session = this.sessionFactory.getCurrentSession();
		 String countQ = "delete from Product ";
		 Query countQuery = session.createQuery(countQ);
		 countQuery.executeUpdate();
		
	}
	@Override
	public void saveAll(List<Product> lp) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		deleteAllId(listpt_part());
		for(Product p:lp){
			save(p);
		}
	}
	@Override
	public void save(Product p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(p);
		
	}
	@Override
	public List<Product> listProductV_CNC(){
		Session session = this.sessionFactory.getCurrentSession();
		List<Product> list = (List<Product>) session.createQuery("from Product where pt_desc1 like '%-V' or pt_desc1 like '%-V_CNC' or pt_desc1 like '%-VA'  order by pt_part").list();
		return list;
	}
	@Override
	public List<String> listpt_part() {
		Session session = this.sessionFactory.getCurrentSession();
		 String countQ = "Select f.id from Product f";
		    Query countQuery = session.createQuery(countQ);
		    List<String> countResults = (List<String>) countQuery.list();
		    
		    return countResults;
	}
	@Override
	public void deleteAllId(List<String> lid) {
		// TODO Auto-generated method stub
		for(String id :lid){
			try{
			delete(id);
			}catch(Exception e){
				logger.debug(e);
				e.printStackTrace();
				continue;
			}
		}
		
		
	}
}
