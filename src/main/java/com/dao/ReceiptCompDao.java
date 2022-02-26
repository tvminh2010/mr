package com.dao;

import java.util.List;

import com.entity.CloseTime;
import com.entity.ReceiptComp;
import com.entity.Turn;
import com.entity.TypeComp;

public interface ReceiptCompDao {

	List<ReceiptComp>  getLPs(String productid);
	public void save(ReceiptComp rc);
	List<ReceiptComp> getbywoname(String woname);
	List<ReceiptComp> getbyturn(String turn);
	List<ReceiptComp> getbyIsWait(Boolean iswait);
	List<ReceiptComp> getbyIsWaitCloseTimeCurrent(Boolean iswait,CloseTime cl);
	ReceiptComp getbyId(String id);
	public void savelist(List<ReceiptComp> rc);
	List<ReceiptComp> getbytype(TypeComp type,Integer page);
	Long getSize();
	ReceiptComp getSetupBSbyDate(String woid);
	List<ReceiptComp>  getbyIsWaitWOid(Boolean iswait,String woid);
	void delete(ReceiptComp rc);
	ReceiptComp getPending(String woid,Turn turn,TypeComp type);
	List<ReceiptComp>  getbyIsPendingWOid(Boolean ispending,String woid);
	List<ReceiptComp>  getbyIsPendingWaitWOid(String woid);
	ReceiptComp getbyClosetimeWoWait(String woid,Integer closetimeid,Boolean wait);
    ReceiptComp getbyturnTypewo(String turn,TypeComp type,String woid);
} 
