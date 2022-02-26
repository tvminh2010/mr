package com.impl;

import java.io.File;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;





import com.dao.TurnDao;
import com.config.Config;
import com.entity.Turn;




@Transactional
public class turnImpl implements TurnDao{
	@Autowired
	private Config config;
	private static final Logger logger = Logger.getLogger(TurnDao.class);
private SessionFactory sessionFactory;
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void save(Turn t) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(t);
		//session.flush();
	}
	@Override
	public void update(Turn t) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(t);
		//session.flush();
	}
	@Override
	public Turn getByid(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		
		String hql = "from Turn where id like :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Turn> list = (List<Turn>) query.list();
		if(list!=null && list.isEmpty()){
			return new Turn();
		}else{
			return list.get(0);
		}
		
	}
	@Override
	public List<Turn> getlinkdownload(int page) {
		Session session = this.sessionFactory.getCurrentSession();
		
		 
		  logger.info(page +"" +config.getPageSize() );
		  SQLQuery selectQuery = session.createSQLQuery("SELECT top 500  id " + 
	    		"      ,d" + 
	    		"      ,linkdownloadbs" + 
	    		"      ,linkdownloadsetup" + 
	    		"      ,downloadedbs" + 
	    		"      ,downloadedsetup" + 
	    		"      ,show" + 
	    		"  FROM Turn where show is null  or show = 1" + 
	    		"  order by d desc")
				  ;
	    selectQuery
		.addScalar("id")
		.addScalar("d")
		.addScalar("linkdownloadbs")
		.addScalar("linkdownloadsetup")
		.addScalar("downloadedbs")
		.addScalar("downloadedsetup")
		.addScalar("show")
		.setResultTransformer(
				Transformers.aliasToBean(Turn.class));
	    List<Turn> l =  (List<Turn>)selectQuery.list();
	    return l;
	}
	@Override
	public Long getSize() {
		Session session = this.sessionFactory.getCurrentSession();
		 String countQ = "Select count (f.id) from Turn f where f.show is null or f.show = 1 ";
		    Query countQuery = session.createQuery(countQ);
		    Long countResults = (Long) countQuery.uniqueResult();
		    
		    return countResults;
	}
	@Override
	public void delete(Turn t) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try{
			if(t.getLinkdownloadbs()!=null && t.getLinkdownloadbs()!=""){
			 File file = new File(config.getDataExcel() + t.getLinkdownloadbs() + ".xls");
			 file.delete();
		     }
			if(t.getLinkdownloadsetup()!=null && t.getLinkdownloadsetup()!=""){
				 File file = new File(config.getDataExcel() + t.getLinkdownloadsetup() + ".xls");
				 file.delete();
			     }
		    t.setShow(false);
		    update(t);
		}catch(Exception e){
			
		}
		
	}



}
