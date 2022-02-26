package com.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.DetailCompDao;
import com.dao.ReceiptCompDao;
import com.entity.CloseTime;
import com.config.Config;
import com.controller.ReportController;
import com.entity.DetailComp;
import com.entity.ReceiptComp;
import com.entity.Turn;
import com.entity.TypeComp;

@Transactional
public class receiptCompImpl implements ReceiptCompDao{
	private static final Logger logger = Logger.getLogger(receiptCompImpl.class);

	@Autowired
	private DetailCompDao dcdao;
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
	public List<ReceiptComp> getLPs(String productid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void save(ReceiptComp rc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(rc);
	}
	@Override
	public List<ReceiptComp> getbyIsWait(Boolean iswait) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query =  session.createQuery("from ReceiptComp where isWait = :iswait and type <> " + TypeComp.Delete.getType() + "  order by date desc" );
		query.setParameter("iswait", iswait);
		query.setMaxResults(100);
		List<ReceiptComp> list = (List<ReceiptComp>)query.list();
		return list;
	}
	@Override
	public List<ReceiptComp> getbyIsWaitWOid(Boolean iswait,String woid) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query =  session.createQuery("from ReceiptComp where isWait = :iswait and wo.id = :woid and type <> " + TypeComp.Delete.getType() )  ;
		query.setParameter("iswait", iswait);
		query.setParameter("woid", woid);
		List<ReceiptComp> list = (List<ReceiptComp>)query.list();
		return list;
	}
	@Override
	public ReceiptComp getbyId(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query =  session.createQuery("from ReceiptComp where id = :id");
		query.setParameter("id", id);
		
		ReceiptComp rc= (ReceiptComp)query.list().get(0);
		
		return rc;
	}
	@Override
	public void savelist(List<ReceiptComp> lrc) {
		// TODO Auto-generated method stub
		for(ReceiptComp rc : lrc){
			save(rc);
			for(DetailComp dc : rc.getlDetailComp()){
				if(dc.getQty()>0){
					savedc(dc);
				}
			}
		}
		
	}
	public void savedc(DetailComp dc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(dc);
		//session.flush();
	}
	@Override
	public List<ReceiptComp> getbywoname(String woname) {
		Session session = this.sessionFactory.getCurrentSession();
		List<ReceiptComp> lrc = new ArrayList<ReceiptComp>();
		Query query =  session.createQuery("from ReceiptComp rc where rc.wo.name like :woname and type <> " + TypeComp.Delete.getType() + " order by rc.date desc");
		query.setParameter("woname", woname);
		lrc = (List<ReceiptComp>)query.list();
		return lrc;
	}
	@Override
	public List<ReceiptComp> getbyturn(String turn) {
		Session session = this.sessionFactory.getCurrentSession();
		List<ReceiptComp> lrc = new ArrayList<ReceiptComp>();
		Query query =  session.createQuery("from ReceiptComp rc where rc.turn.id like :turn and type <> " + TypeComp.Delete.getType() + " order by rc.date desc");
		query.setParameter("turn", turn);
		lrc = (List<ReceiptComp>)query.list();
		return lrc;
	}
	@Override
	public ReceiptComp getbyturnTypewo(String turn,TypeComp type,String woid) {
		Session session = this.sessionFactory.getCurrentSession();
		ReceiptComp lrc = new ReceiptComp();
		Query query =  session.createQuery("from ReceiptComp rc where rc.turn.id like :turn and rc.type = :type and  wo.id like :woid   order by rc.date desc");
		query.setParameter("turn", turn);
		query.setParameter("type", type);
		query.setParameter("woid", woid);
		lrc = (ReceiptComp)query.uniqueResult();
		return lrc;
	}
	@Override
	public List<ReceiptComp> getbytype(TypeComp type, Integer page) {
		Session session = this.sessionFactory.getCurrentSession();
		List<ReceiptComp> lrc = new ArrayList<ReceiptComp>();
		String hql = "";
		if(type != null){
			
			hql = "from ReceiptComp rc where rc.type = :type order by rc.date desc";
			Query query =  session.createQuery(hql);
			query.setParameter("type", type);
			lrc = (List<ReceiptComp>)query.setFirstResult((page-1)*config.getPageSize())
					.setMaxResults(config.getPageSize()).list();
		}else{
			hql = "from ReceiptComp rc where  type <> " + TypeComp.Delete.getType() + "  order by rc.date desc";
			Query query =  session.createQuery(hql);
			lrc = (List<ReceiptComp>)query.setFirstResult((page-1)*config.getPageSize())
					.setMaxResults(config.getPageSize()).list();
		}
		
		
		return lrc;
	}
	@Override
	public Long getSize() {
		Session session = this.sessionFactory.getCurrentSession();
		 String countQ = "Select count (f.id) from ReceiptComp f";
		    Query countQuery = session.createQuery(countQ);
		    Long countResults = (Long) countQuery.uniqueResult();
		    
		    return countResults;
	}

	@Override
	public ReceiptComp getSetupBSbyDate(String woid) {
		Session session = this.sessionFactory.getCurrentSession();
		ReceiptComp rc = new ReceiptComp();
		Query query =  session.createQuery("from ReceiptComp  where isWait = 0 and isPending = 1 and  wo.id like :woid and type <> " + TypeComp.Delete.getType() + " order by date desc");
		query.setParameter("woid", woid);
		List<ReceiptComp> lrc = (List<ReceiptComp>)query.setMaxResults(1).list();
	  if(lrc !=null && lrc.size()>0){
		  return lrc.get(0);
	  }else{
		return rc;
	  }
	}
	@Override
	public void delete(ReceiptComp rc) {
		// TODO Auto-generated method stub
		logger.info("delete ReceiptComp rcid= " + rc.getId());

		Session session = this.sessionFactory.getCurrentSession();
	/*	for(DetailComp dc : rc.getlDetailComp()){
			dcdao.delete(dc);
		}*/
		rc.setType(TypeComp.Delete);
		rc.setDeleteddate(new Timestamp(new Date().getTime()) );
		session.update(rc);
		session.flush();
	}
	@Override
	public List<ReceiptComp> getbyIsWaitCloseTimeCurrent(Boolean iswait,
			CloseTime cl) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query =  session.createQuery("from ReceiptComp where isWait = :iswait and closetime = :cl and type <> " + TypeComp.Delete.getType());
		query.setParameter("iswait", iswait);
		query.setParameter("cl", cl);
		List<ReceiptComp> list = (List<ReceiptComp>)query.list();
		return list;
	}
	@Override
	public ReceiptComp getPending(String woid,Turn turn,TypeComp type) {
		Session session = this.sessionFactory.getCurrentSession();
		if(turn == null){
		Query query =  session.createQuery("from ReceiptComp where type=:type and wo.id = :woid and isPending = 1");
		query.setParameter("woid", woid);
		query.setParameter("type", type);
		ReceiptComp rc = new ReceiptComp();
		List<ReceiptComp> list = (List<ReceiptComp>)query.list();
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return rc;
		}
		}else{
			Query query1 =  session.createQuery("from ReceiptComp where wo.id = :woid and isPending = 1 and turn.id = :turn  and type <> " + TypeComp.Delete.getType() + " ");
			query1.setParameter("woid", woid);
			query1.setParameter("turn", turn.getId());
			ReceiptComp rc = new ReceiptComp();
			List<ReceiptComp> list = (List<ReceiptComp>)query1.list();
			if(list!=null && list.size()>0){
				return list.get(0);
			}else{
				return rc;
			}
		}
		
		
	
	}
	@Override
	public List<ReceiptComp> getbyIsPendingWOid(Boolean ispending, String woid) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query =  session.createQuery("from ReceiptComp where isPending = :ispending and wo.id = :woid and type <> " + TypeComp.Delete.getType() );
		query.setParameter("ispending", ispending);
		query.setParameter("woid", woid);
		List<ReceiptComp> list = (List<ReceiptComp>)query.list();
		return list;
	}
	@Override
	public List<ReceiptComp> getbyIsPendingWaitWOid(String woid) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query =  session.createQuery("from ReceiptComp where  isWait = :wait and wo.id = :woid and type <> " + TypeComp.Delete.getType() + " order by closetime.closetime");
		
		query.setParameter("wait", true);
		query.setParameter("woid", woid);
		List<ReceiptComp> list = (List<ReceiptComp>)query.list();
		return list;
	}
	@Override 
	public ReceiptComp getbyClosetimeWoWait(String woid, Integer closetimeid, Boolean wait) {
		Session session = this.sessionFactory.getCurrentSession();
		ReceiptComp rc = new ReceiptComp();
		Query query =  session.createQuery("from ReceiptComp  where isWait = :wait  and  wo.id like :woid and closetime.id = :closetimeid and type <> " + TypeComp.Delete.getType() + " order by date desc");
		query.setParameter("woid", woid);
		query.setParameter("closetimeid", closetimeid);
		query.setParameter("wait", wait);
		List<ReceiptComp> lrc = (List<ReceiptComp>)query.list();
	  if(lrc !=null && lrc.size()>0){
		  return lrc.get(0);
	  }else{
		return rc;
	  }
	}
}
