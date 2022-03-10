package com.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.WorkOrderDao;
import com.entity.CloseTime;
import com.config.Config;
import com.entity.Product;
import com.entity.TypeComp;
import com.entity.WorkOrder;
import com.lctech.service.ExportExcel;
@Transactional
public class WorkOrderImpl implements WorkOrderDao {
	private static final Logger logger = Logger.getLogger(WorkOrderImpl.class);
	
private SessionFactory sessionFactory;
@Autowired
private Config config;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public List<WorkOrder> getListWorkOrderByStatus(Integer status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrder> getListPage( Date startdate, Date enddate,Integer page,String workorder,String linename,String item) {
		Session session = this.sessionFactory.getCurrentSession();
		
		
		  String hql = "From WorkOrder wo where  wo.createdate >= :startdate and wo.createdate <= :enddate ";
		  if(workorder!=null && workorder !=""){
				  hql = hql + " and wo.name like '%" + workorder + "%'";
		  }
		  if(linename!=null && linename !=""){
			  hql = hql + " and wo.line.name like '%" + linename + "%'";
	  }
		  if(item!=null && item !=""){
			  hql = hql + " and wo.model.pt_desc1 like '%" + item + "%'";
	  }
		  String orderby = " order by wo.createdate desc";
		  List<WorkOrder> lastPage = new ArrayList<WorkOrder>();
		  if(page==null){
		    Query selectQuery = session.createQuery(hql+orderby);
		    selectQuery.setParameter("startdate", startdate);
		    selectQuery.setParameter("enddate", enddate);
		    lastPage =  (List<WorkOrder>)selectQuery.list();
		  }else if(page!=null && page>0){
			  Query selectQuery = session.createQuery(hql+orderby).setMaxResults(config.getPageSize())
			    		.setFirstResult((page - 1) * config.getPageSize());
			    selectQuery.setParameter("startdate", startdate);
			    selectQuery.setParameter("enddate", enddate);
			  lastPage =  (List<WorkOrder>)selectQuery.list();
		  }
		return lastPage;
	}

	@Override
	public Long getSize() {
		Session session = this.sessionFactory.getCurrentSession();
		 String countQ = "Select count (f.id) from WorkOrder f";
		    Query countQuery = session.createQuery(countQ);
		    Long countResults = (Long) countQuery.uniqueResult();
		    
		    return countResults;
	}

	@Override
	@Transactional
	public WorkOrder saveList(List<WorkOrder> listWO) {
		// TODO Auto-generated method stub
		WorkOrder wo1 = new WorkOrder();
		Session session = this.sessionFactory.getCurrentSession();
		for(WorkOrder wo : listWO){
			wo.setName(wo.getName().trim());
			try{
				String id = (String)session.save(wo);
				
				session.flush();
				
			}catch(Exception e){
				logger.info(e);
				e.printStackTrace();
				session.close();
				return wo;
				
			}
		
		}
		return null;
	}

	@Override
	public WorkOrder save(WorkOrder wo) {
		WorkOrder wo1 = new WorkOrder();
		Session session = this.sessionFactory.getCurrentSession();
		try{
	
		wo.setName(wo.getName().trim());
		 session.saveOrUpdate(wo);
		session.flush();
		return null;
		}catch(Exception e){
			logger.info(e);
			e.printStackTrace();
			return wo;
		}
		
	}

	@Override
	public List<WorkOrder> getListWorkOrderByLine(Integer lineid) {
		Session session = this.sessionFactory.getCurrentSession();
		    Query selectQuery = session.createQuery("From WorkOrder wo where wo.line.id = :lineid");
		    selectQuery.setParameter("lineid", lineid);
		    List<WorkOrder> lastPage =  (List<WorkOrder>)selectQuery.list();
		return lastPage;
	}
	
	@Override
	public List<WorkOrder> getListbyDate(Date d) {
		Session session = this.sessionFactory.getCurrentSession();
		    Query selectQuery = session.createQuery("From WorkOrder wo where wo.createdate > :createdate");
		    selectQuery.setParameter("createdate", d);
		    List<WorkOrder> lastPage =  (List<WorkOrder>)selectQuery.list();
		return lastPage;
	}
	@Override
	public Product getProduct(String woid) {
		Session session = this.sessionFactory.getCurrentSession();
	    Query selectQuery = session.createQuery("From WorkOrder wo where id = :woid");
	    selectQuery.setParameter("woid", woid);
	    Product p =  (Product)selectQuery.list().get(0);
	    return p;
	}

	@Override
	public WorkOrder getWObyLineProduct(String productid, Integer lineid,Integer status) {
		logger.info(productid + "_" + lineid + "_" + status);
		Session session = this.sessionFactory.getCurrentSession();
	    Query selectQuery = session.createQuery("From WorkOrder wo "
	    		+ "where wo.line.id = :lineid "
	    		+ "and wo.model.pt_desc1 like :productid"
	    		+ " and wo.status in (:status) ");
	    selectQuery.setParameter("lineid", lineid);
	    selectQuery.setParameter("productid", productid);
	    selectQuery.setParameter("status", status);
	    WorkOrder w =  (WorkOrder)selectQuery.list().get(0);
	return w;
	}

	@Override
	public WorkOrder getWObyId(String woid) {
		Session session = this.sessionFactory.getCurrentSession();
	    Query selectQuery = session.createQuery("From WorkOrder wo where wo.id like :woid");
	    selectQuery.setParameter("woid", woid);
	    WorkOrder wo = new WorkOrder();
	    
	    List<WorkOrder> lwo =  (List<WorkOrder>)selectQuery.list();
	    if(!lwo.isEmpty() && lwo.size()>0 ){
	    	return lwo.get(0);
	    }
	    else{
	    	return wo;
	    }

	}

	@Override
	public List<WorkOrder> getListWorkOrderByLineStatus(Integer lineid
		) {
		Session session = this.sessionFactory.getCurrentSession();
	    Query selectQuery = session.createQuery("From WorkOrder wo where wo.line.id = :lineid and wo.status in (1,2) ");
	    selectQuery.setParameter("lineid", lineid);
	
	    List<WorkOrder> lastPage =  (List<WorkOrder>)selectQuery.list();
	return lastPage;
	}
	@Override
	public List<WorkOrder> getListWorkOrderByLineStatusIsSetup(Integer lineid
		) {
		Session session = this.sessionFactory.getCurrentSession();
	    Query selectQuery = session.createQuery("From WorkOrder wo where wo.line.id = :lineid and wo.status=1 ");
	    selectQuery.setParameter("lineid", lineid);
	
	    List<WorkOrder> lastPage =  (List<WorkOrder>)selectQuery.list();
	return lastPage;
	}
	@Override
	public List<WorkOrder> getListWorkOrderByLineStatusIsBx(Integer lineid
		) {
		Session session = this.sessionFactory.getCurrentSession();
	    Query selectQuery = session.createQuery("From WorkOrder wo where wo.line.id = :lineid and wo.status=2 ");
	    selectQuery.setParameter("lineid", lineid);
	
	    List<WorkOrder> lastPage =  (List<WorkOrder>)selectQuery.list();
	return lastPage;
	}
	@Override
	public List<Object[][]> getDetailTotal(String woid) {
		Session session = this.sessionFactory.getCurrentSession();
	    Query selectQuery = session.createQuery("select "
	    		+ "  dc.model.pt_part ,"
	    		+ " sum(case when rc.type =0 then dc.qty else 0 end) ,"
	    		+ "sum(case when rc.type =1 then dc.qty else 0 end) ,"
	    		+ "sum(case when rc.type =2 then dc.qty else 0 end) ,"
	    		+ "sum(case when rc.type =3 then dc.qty else 0 end) "
	    		+ " From DetailComp as dc left  join dc.receipt as rc left join rc.wo as wo "
	    		+ "  where wo.id = :woid "
	    		+ " group by dc.model.pt_part  ");
	    selectQuery.setParameter("woid", woid);
	 
	    List<Object[][]> lo =  (List<Object[][]>)selectQuery.list();
	 
	return lo;
	}

	@Override
	public List<String> getCowo(boolean coing) {
		Session session = this.sessionFactory.getCurrentSession();
		List<String> lwoname = new ArrayList<String>();
		if(coing)//mở{
		{
			Query selectQuery = session.createQuery("select name From WorkOrder wo where wo.status =2 order by wo.createdate desc ");
			lwoname = (List<String>)selectQuery.setMaxResults(1000).list();
		}else{
			Query selectQuery1 = session.createQuery("select name From WorkOrder wo where wo.status =3 order by wo.createdate desc ");
			lwoname = (List<String>)selectQuery1.setMaxResults(1000).list();
		}
			
	    
	    
	return lwoname;
	}

	@Override
	public WorkOrder getWObyName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
	    Query selectQuery = session.createQuery("From WorkOrder wo where wo.name like :name");
	    selectQuery.setParameter("name", name);
	    WorkOrder w = new WorkOrder();
	    List<WorkOrder> lwo =  (List<WorkOrder>)selectQuery.list();
	    if(!lwo.isEmpty() && lwo.size()>0){
	    	return lwo.get(0);
	    }else{
	    	return w;
	    }
	
	}
	@Override
	public void delete(WorkOrder ct) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(ct);
		session.flush();
		
	}
	@Override
	public void deletelist(String lct) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete  WorkOrder where id in(" +lct+ ")");
		query.executeUpdate();
		session.flush();
	}

	@Override
	public WorkOrder checkWObyName(String name, String woid) {
		Session session = this.sessionFactory.getCurrentSession();
	    Query selectQuery = session.createQuery("From WorkOrder wo where wo.name like :name and wo.id <> :woid");
	    selectQuery.setParameter("name", name);
	    selectQuery.setParameter("woid", woid);
	    WorkOrder w = new WorkOrder();
	    List<WorkOrder> lwo =  (List<WorkOrder>)selectQuery.list();
	    if(!lwo.isEmpty() && lwo.size()>0){
	    	return lwo.get(0);
	    }else{
	    	return w;
	    }
	}
}
