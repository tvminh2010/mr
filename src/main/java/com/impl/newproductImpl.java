package com.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.dao.NewProductDao;

import com.entity.NewProduct;

@Transactional
public class newproductImpl implements NewProductDao {

	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Override
	public List<NewProduct> listNewProductAll(){
		Session session = this.sessionFactory.getCurrentSession();
		List<NewProduct> list = (List<NewProduct>) session.createQuery("from NewProduct").list();
		return list;
	}
	@Override
	public List<NewProduct> listNewProduct(){
		Session session = this.sessionFactory.getCurrentSession();
		List<NewProduct> list = (List<NewProduct>) session.createQuery("from NewProduct where disa = 0 or disa is null").list();
		return list;
	}
	@Override
	public List<NewProduct> listNewProductV_CNC(){
		Session session = this.sessionFactory.getCurrentSession();
		List<NewProduct> list = (List<NewProduct>) session.createQuery("from NewProduct where pt_desc1 like '%-V' or pt_desc1 like '%-V_CNC' order by name").list();
		return list;
	}

	@Override
	public List<NewProduct> listNewProduct2(){
		Session session = this.sessionFactory.getCurrentSession();
		List<NewProduct> list = (List<NewProduct>) session.createQuery("select p.NewProduct_id, p.name, p.pt_desc2,p.pt_prod_line, p.pt_um, vend.addr,vend.sort  "
				+ "from NewProduct p  left join p.pt_vend as vend ").list();
		return list;
	}
	@Override
	public List<NewProduct> listPST(){
		Session session = this.sessionFactory.getCurrentSession();
		List<NewProduct> list = (List<NewProduct>) session.createQuery("from NewProduct p  left join p.pt_vend as vend ").list();
		return list;
	}
	public Integer getNewProductId() {
		return null;
	}
	
	public void createNewProduct(String name) {
	}
	@Override
	public void insert(NewProduct p){
		Session session = this.sessionFactory.getCurrentSession();		
		session.save(p);
		session.flush();
        session.clear();
	}
	@Override
	public void delete(String id){
		   Session session = this.sessionFactory.getCurrentSession();
		   int result = session.createQuery("delete NewProduct where id = '"+id+"'").executeUpdate();
	   }
	@Override
	public void delete(NewProduct model){
		   Session session = this.sessionFactory.getCurrentSession();
		   session.delete(model);
	   }
	@Override
	public void update(NewProduct model){
		   Session session = this.sessionFactory.getCurrentSession();
		   session.update(model);
	   }

	@Override
	public NewProduct getNewProductById(String id) {
		 Session session = this.sessionFactory.getCurrentSession();
		 String hql = "from NewProduct where id='"+id+"'";
		 Query query = session.createQuery(hql);
		 List<NewProduct> NewProduct = query.list();
		 NewProduct pro = new NewProduct();
		 if(NewProduct.size()>0){
		  pro = NewProduct.get(0);
		  return pro;
		 }else{
			 return null;
		 }
		
	}
	@Override
	public NewProduct getNewProductByName(String name) {
		 Session session = this.sessionFactory.getCurrentSession();
		 String hql = "from NewProduct where name='"+name+"'";
		 Query query = session.createQuery(hql);
		 List<NewProduct> NewProduct = query.list();
		 if(NewProduct.size()>0){
		 NewProduct pro = NewProduct.get(0);
		 return pro;
		 }else{
			 return new NewProduct();
		 }
		
	}
}
