package com.dao;

import java.util.Date;
import java.util.List;

import com.entity.CloseTime;
import com.entity.DetailComp;
import com.entity.ReceiptComp;
import com.entity.Turn;
import com.entity.TypeComp;

public interface DetailCompDao {

	List<DetailComp> getlistDetailComp(String woid);
	List<DetailComp> getlistDetailCompbyreceiptid(String woid);
	DetailComp getDCbyId(String id);
	public void save(DetailComp dc);
	public void update(DetailComp dc);

	List<String> getListDesc2(TypeComp type,CloseTime lnt);//type bs setup
	List<Object[]> getListByDesc2(TypeComp type,String desc2,CloseTime lnt);//type bs setup
	List<Object[]> getListByNoDesc2(TypeComp type,CloseTime lnt);//type bs setup
	public void save(List<DetailComp> ldc);
	List<DetailComp> getlistDetailCompbytid(String rcid);
	void delete(DetailComp dc);
	
	List<String> reportwoLDesc2(Date startdate,Date enddate,String woname,String item,String line);
	
	List<Object[]> reportwo(Date startdate,Date enddate,Integer page,String woname,String item,String line);
	List<Object[]> reportwo1(Date startdate,Date enddate,Integer page,String woname,String item,String line);
	
	List<Object[]> bcwo11(String woid);
	
	Turn getTurnSetup(String woid);
	List<Object[]> bcwo12Setup(String woid,String turn);
	List<Object[]> bcwo12BS(String woid,String turn);
	List<Object[]> getDetailCompByWoidSetupBS(String woid);
	List<Object[]> getDetailCompByWoidReponse( String turn , String pt_part, String woid);
}

