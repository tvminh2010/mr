package com.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.DetailCompDao;
import com.entity.CloseTime;
import com.config.Config;
import com.entity.DetailComp;
import com.entity.Turn;
import com.entity.TypeComp;
@Transactional
public class detailCompImpl implements DetailCompDao{
private SessionFactory sessionFactory;
	
@Autowired
private Config config;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Override
	public List<DetailComp> getlistDetailComp(String woid) {
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("from DetailComp where ReceiptComp.wo.id like :woid and ReceiptComp.type <> " + TypeComp.Delete.getType() + " ");
		query.setParameter("woid", woid);
	
		List<DetailComp> list = (List<DetailComp>) query.list();
		
		return list;
	}
	@Override
	public DetailComp getDCbyId(String id) {
		return null;
	}
	@Override
	public void save(DetailComp dc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(dc);
		session.flush();
	}

	@Override
	public void update(DetailComp dc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(dc);
		session.flush();
	}

	@Override
	public void save(List<DetailComp> ldc) {
		Session session = this.sessionFactory.getCurrentSession();
		for(DetailComp dc : ldc){
			save(dc);
		}
		
	}

	@Override
	public List<DetailComp> getlistDetailCompbyreceiptid(String woid) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		Query query = session.createQuery("from DetailComp dc  where dc.receipt.isWait = 1 and  dc.receipt.wo.id like :woid and dc.receipt.type <> " + TypeComp.Delete.getType() );
		query.setParameter("woid", woid);
	
		List<DetailComp> list = (List<DetailComp>) query.list();
		
		return list;
	}
	@Override
	public List<DetailComp> getlistDetailCompbytid(String rcid) {
Session session = this.sessionFactory.getCurrentSession();	
		
		Query query = session.createQuery("from DetailComp dc  where dc.receipt.id like :rcid  and dc.receipt.type <> " + TypeComp.Delete.getType() + " ");
		query.setParameter("rcid", rcid);
	
		List<DetailComp> list = (List<DetailComp>) query.list();
		
		return list;
	}
	@Override
	public void delete(DetailComp dc) {
		Session session = this.sessionFactory.getCurrentSession();
		
			session.delete(dc);
			//session.flush();
		
	}
	@Override
	public List<String> getListDesc2(TypeComp type, CloseTime lnt) {
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("select distinct model.pt_desc2 from DetailComp dc  join dc.receipt rc left join dc.model model "
				+ "where dc.qty>0 and rc.isWait =1 and closetime = :lnt  and rc.type = :type and model.pt_desc2 <> ''  order by model.pt_desc2");

		query.setParameter("type", type);
		query.setParameter("lnt", lnt);
		List<String> list = (List<String>) query.list();
		
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getListByDesc2(TypeComp type, String desc2,
			CloseTime lnt) {
		Session session = this.sessionFactory.getCurrentSession();	
	   /* Query query = session.createQuery("select distinct dc,rc,model,line from DetailComp dc  right join dc.receipt rc right join dc.model model join rc.wo wo  join wo.line line "
				+ "where  dc.qty>0 and rc.isWait = 1 and closetime = :lnt  and  rc.type = :type and model.pt_desc2 like :desc2  order by  line.name,rc.date desc,dc.model.pt_desc2,dc.model.pt_desc1");
		*/Query query = session.createQuery("select   line.name,model1.pt_desc1,model.pt_desc1,model.pt_desc2,model.pt_part,model.pt_um,dc.qty,wo.name , max(rc.date) "
				+ "from DetailComp dc  left join dc.receipt rc left join dc.model model left join rc.wo wo "
				+ "left join wo.line line left join wo.model model1  "
				+ "where  dc.qty>0 and rc.isWait = 1 and closetime = :lnt  and  rc.type = :type and model.pt_desc2 like :desc2  "
				+ " group by line.name,model1.pt_desc1,model.pt_desc1,model.pt_desc2,model.pt_part,model.pt_um,dc.qty,wo.name order by  line.name, max(rc.date) desc,dc.model.pt_desc2,dc.model.pt_desc1 ");
		
		query.setParameter("type", type);
		query.setParameter("desc2", desc2);
		query.setParameter("lnt", lnt);
		List<Object[]> list = (List<Object[]>) query.list();
		return list;
	}
	@Override
	public List<Object[]> getListByNoDesc2(TypeComp type, CloseTime lnt) {
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("select  line.name,model1.pt_desc1,model.pt_desc1,model.pt_desc2,model.pt_part,model.pt_um,dc.qty,wo.name,max(rc.date)  "
				+ "from DetailComp dc  left join dc.receipt rc left join dc.model model left join rc.wo wo left join wo.model model1 left join wo.line line "
				+ "where dc.qty>0 and rc.isWait = 1 and closetime = :lnt  and rc.type = :type "
				+ "group by line.name,model1.pt_desc1,model.pt_desc1,model.pt_desc2,model.pt_part,model.pt_um,dc.qty,wo.name "
				+ "order by line.name, max(rc.date) desc,model.pt_desc2,model.pt_desc1  ");
		
		query.setParameter("type", type);
		query.setParameter("lnt", lnt);
		List<Object[]> list = (List<Object[]>) query.list();
		return list;
	}
	@Override
	public List<String> reportwoLDesc2(Date startdate, Date enddate,String woname,String item,String line) {
		Session session = this.sessionFactory.getCurrentSession();	
	
		String select = "select distinct model1.pt_desc2 " ;
		String sfrom =  " from  DetailComp dc  left join dc.receipt rc left join dc.model model1 left join rc.wo wo join wo.line line join wo.model model ";
		String swhere = " where dc.qty>0 and wo.createdate >= :startdate and wo.createdate <= :enddate  and dc.receipt.type <> " + TypeComp.Delete.getType() ;
		if(woname!=null && woname!=""){
			swhere = swhere + " and wo.name like '%" + woname + "%' ";
		}
		if(item!=null && item !=""){
			swhere = swhere + " and model.pt_desc1 like '%" + item + "%' ";
		}
		if(line!=null && line !=""){
			swhere = swhere + " and line.name like '%" + line + "%' ";
		}	
		String orderby = " order by model1.pt_desc2";
		Query query = session.createQuery(select+sfrom+swhere+orderby);
		query.setParameter("startdate", startdate);
		query.setParameter("enddate", enddate);
		List<String> list = (List<String>) query.list();
		return list;
	}
	@Override
	public List<Object[]> reportwo(Date startdate, Date enddate,Integer page,String woname,String item,String line) {
		// TODO Auto-generated method stub
		
		List<String> listPt_desc2 = reportwoLDesc2( startdate,  enddate,woname,item,line);
		String select = " select wo.name,wo.createdate,line.name,model.pt_desc1,wo.qty,wo.status ";
		String lsumbyDesc2 = "";
		int i=0;
		for(String desc2:listPt_desc2){
			String sumbyDesc2 =" ,sum(case when model1.pt_desc2  = '"+desc2+"' then  dc.qty else 0 end) as r" + i;
			lsumbyDesc2 = lsumbyDesc2 + sumbyDesc2;
		}
		String sfrom =  " from  DetailComp dc  left join dc.receipt rc left join dc.model model1 left join rc.wo wo join wo.line line join wo.model model ";
		String swhere = " where dc.qty>0 and wo.createdate >= :startdate and wo.createdate <= :enddate  and dc.receipt.type <> " + TypeComp.Delete.getType() + " ";
		if(woname!=null && woname!=""){
			swhere = swhere + " and wo.name like '%" + woname + "%' ";
		}
		if(item!=null && item !=""){
			swhere = swhere + " and model.pt_desc1 like '%" + item + "%' ";
		}
		if(line!=null && line !=""){
			swhere = swhere + " and line.name like '%" + line + "%' ";
		}	
			String group = 	 " group by wo.name,wo.createdate,line.name,model.pt_desc1,wo.qty,wo.status order by wo.name ";
		Session session = this.sessionFactory.getCurrentSession();	
		List<Object[]> list  = new ArrayList<Object[]>();
		if(page==null){
			Query query = session.createQuery(select +lsumbyDesc2 + sfrom + swhere +group );
		    		query.setParameter("startdate", startdate);
		    		query.setParameter("enddate", enddate);
		    		 list = (List<Object[]>) query.list();
		}else{
		Query query = session.createQuery(select + lsumbyDesc2 + sfrom + swhere +group ).setMaxResults(config.getPageSize())
	    		.setFirstResult((page - 1) * config.getPageSize());
		query.setParameter("startdate", startdate);
		query.setParameter("enddate", enddate);
		 list = (List<Object[]>) query.list();
		}
		
		return list;
		
		
	}
	@Override
	public List<Object[]> bcwo11(String woid) {
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("select model.pt_part,model.pt_desc1,model.pt_desc2, "
				+ "sum(case when rc.type = 2 then dc.qty else 0 end) as GN, "
				+ "sum(case when rc.type =3  then dc.qty else 0 end) as HT "
				+ "from DetailComp dc  right join dc.receipt rc right join dc.model model left join rc.wo  wo "
				+ "where dc.qty > 0 and wo.id = :woid  and dc.receipt.type <> " + TypeComp.Delete.getType() + " "
				+ " group by model.pt_part,model.pt_desc1,model.pt_desc2  ");
		
		query.setParameter("woid", woid);
		
		List<Object[]> list = (List<Object[]>) query.list();
		return list;
	}
	@Override
	public Turn getTurnSetup(String woid) {
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("select turn "
			
				+ " from ReceiptComp rc left join rc.wo  wo left join rc.turn turn "
				+ "where wo.id = :woid and type = 0 ") ;
		
		
		query.setParameter("woid", woid);
		
		List<Turn> list = (List<Turn>) query.list();
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	
	}
	@Override
	public List<Object[]> bcwo12Setup(String woid, String turn) {
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("select model.pt_part,model.pt_desc1,model.pt_desc2, "
				+ "sum(case when rc.type = 2 then dc.qty else 0 end) as GN "
				
				+ "from DetailComp dc  right join dc.receipt rc right join dc.model model left join rc.wo  wo "
				+ "where dc.qty > 0 and wo.id = :woid and rc.turn.id like :turn  "
				+ " group by model.pt_part,model.pt_desc1,model.pt_desc2  ");
		
		query.setParameter("woid", woid);
		query.setParameter("turn", turn);
		List<Object[]> list = (List<Object[]>) query.list();
		return list;
	}
	@Override
	public List<Object[]> bcwo12BS(String woid, String turn) {
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("select model.pt_part,model.pt_desc1,model.pt_desc2, "
				+ "sum(case when rc.type = 2 then dc.qty else 0 end) as GN "
				
				+ "from DetailComp dc  right join dc.receipt rc right join dc.model model left join rc.wo  wo "
				+ "where dc.qty > 0 and wo.id = :woid and rc.turn.id <> :turn  "
				+ " group by model.pt_part,model.pt_desc1,model.pt_desc2  ");
		
		query.setParameter("woid", woid);
		query.setParameter("turn", turn);
		List<Object[]> list = (List<Object[]>) query.list();
		return list;
	}
	@Override
	public List<Object[]> reportwo1(Date startdate, Date enddate, Integer page,
			String woname, String item, String line) {
		
		String select = " select wo.name,wo.createdate,line.name,model.pt_desc1,wo.qty,wo.status ";
		
	
		
	    String sumbyDesc2 =" ,model1.pt_part,model1.pt_desc1,model1.pt_desc2, sum(case when rc.type = 2 then dc.qty else 0 end) as GN ,sum(case when rc.type = 3 then dc.qty else 0 end) as HT";
		
		
		String sfrom =  " from  DetailComp dc  left join dc.receipt rc left join dc.model model1 left join rc.wo wo join wo.line line join wo.model model ";
		String swhere = " where dc.qty>0  and wo.createdate <= :enddate ";
		if(woname!=null && woname!=""){
			swhere = swhere + " and wo.name like '%" + woname + "%' ";
		}
		if(item!=null && item !=""){
			swhere = swhere + " and model.pt_desc1 like '%" + item + "%' ";
		}
		if(line!=null && line !=""){
			swhere = swhere + " and line.name like '%" + line + "%' ";
		}	
		if(startdate!=null){
			 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			 String da = dateFormat.format(startdate);
			swhere = swhere + " and wo.createdate >= '"+ da +"' " ;
		}	
			String group = 	 " group by wo.name,wo.createdate,line.name,model.pt_desc1,wo.qty,wo.status,model1.pt_part,model1.pt_desc1,model1.pt_desc2 order by wo.createdate desc ,model1.pt_desc1 asc";
		Session session = this.sessionFactory.getCurrentSession();	
		List<Object[]> list  = new ArrayList<Object[]>();
		if(page==null){
			Query query = session.createQuery(select +sumbyDesc2 + sfrom + swhere +group );
		    		
		    		query.setParameter("enddate", enddate);
		    		 list = (List<Object[]>) query.list();
		}else{
		Query query = session.createQuery(select + sumbyDesc2 + sfrom + swhere +group ).setMaxResults(config.getPageSize())
	    		.setFirstResult((page - 1) * config.getPageSize());
		
		query.setParameter("enddate", enddate);
		 list = (List<Object[]>) query.list();
		}
		
		return list;
	}
	@Override
	public List<Object[]> getDetailCompByWoidSetupBS(String woname) {
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("select dc , rc "
				+ "from DetailComp dc  right join dc.receipt rc right join dc.model model  left join rc.wo wo left join rc.turn turn "
				+ "where wo.name like :woname and dc.qty>0 and rc.type in (0,1) order by turn.id ");
		
		query.setParameter("woname", woname);
		
		List<Object[]> list = (List<Object[]>) query.list();
		return list;
	}
	@Override
	public List<Object[]> getDetailCompByWoidReponse(String turn, String pt_part, String woid) {
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("select dc ,rc "
				+ "from DetailComp dc  right join dc.receipt rc left join dc.model model left join rc.turn turn  "
				+ "where  dc.qty>0 and model.pt_part like :pt_part and turn.id like :turn and rc.type =2 and rc.wo.id = :woid ");
		
		query.setParameter("turn", turn);
		query.setParameter("pt_part", pt_part);
		query.setParameter("woid", woid);
		List<Object[]> list = (List<Object[]>) query.list();
		return list;
	}
	

}
