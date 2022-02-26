package com.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.PsProductDao;
import com.config.Config;
import com.controller.AdminController;
import com.entity.Product;
import com.entity.PsProduct;
@Transactional
public class psproductImpl implements PsProductDao{
	private static final Logger logger = Logger.getLogger(AdminController.class);

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
	public List<PsProduct> getLPs(String productid) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from PsProduct where  ps_par.pt_part =:productid order by ps_comp.pt_desc2, ps_comp.pt_desc1");
		query.setParameter("productid", productid);
		List<PsProduct> listps = (List<PsProduct>) query.list();//get line p1 p2 p3 sample
	
		
		return listps;
	}
	@Override
	public List<PsProduct> getLPs() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<PsProduct> list = (List<PsProduct>) session.createQuery("from PsProduct where ps_par is not null and ps_comp is not null ")
				
				.list();
		return list;
	}

	@Override
	public Long getSize() {
		Session session = this.sessionFactory.getCurrentSession();
		 String countQ = "Select count (f.id) from PsProduct f";
		    Query countQuery = session.createQuery(countQ);
		    Long countResults = (Long) countQuery.uniqueResult();
		    
		    return countResults;
	}
	@Override
	public void deleteAll() {
		Session session = this.sessionFactory.getCurrentSession();
		 String countQ = "delete from PsProduct ";
		 Query countQuery = session.createQuery(countQ);
		 countQuery.executeUpdate();
		
	}
	@Override
	public PsProduct saveAll(List<PsProduct> lp) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		PsProduct ps = new PsProduct();
		try{
			deleteAll();
			for(PsProduct p:lp){
				
			 save(p);
			}
			session.flush();
		}catch(Exception e){
			logger.debug(e);
			e.printStackTrace();
			return ps;
		}
		return new PsProduct();
		
		
	}
	@Override
	public void save(PsProduct p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(p);
		
		
	}
	@Override
	public void delete(PsProduct p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(p);
		
	}
	@Override
	public PsProduct getByName(String pid, String psid) {
	Session session = this.sessionFactory.getCurrentSession();
		PsProduct ps = new PsProduct();
		Query query =  session.createQuery("from PsProduct where ps_par.pt_part like :pid and ps_comp.pt_part like :psid ");
		query.setParameter("pid", pid);
		query.setParameter("psid", psid);
		
		List<PsProduct> list = (List<PsProduct>) query.list();
		if(!list.isEmpty() && list.size()>0){
			ps = list.get(0);
		}
		return ps;
	}

}
