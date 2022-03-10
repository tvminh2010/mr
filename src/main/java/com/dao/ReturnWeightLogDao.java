package com.dao;

import java.util.Date;
import java.util.List;

import com.entity.CloseTime;
import com.entity.ReturnWeightLog;

public interface ReturnWeightLogDao {
   public void save(ReturnWeightLog ct);
   public Object[]  getLotno(String woid, String model);
   public List<ReturnWeightLog> getReturnWeightLogBywoid(String woid);
   public List<ReturnWeightLog> getList(Integer page,String woname, String item,String serialno);
   public Long countAll(String woname, String item,String serialno);
	public List<Object[][]> getReturnWeightLogBywoidOrderbyNVL(String woid);
	public void updateStatus(Long id);
	public ReturnWeightLog getReturnWeightLog(Long id);
	public void delete(Long id);
	List<ReturnWeightLog> getByDate(Date createdate);
	List<Object[][]> getReturnWeightLogBywoidOrderbyNVL0(String woid);
	List<ReturnWeightLog> getReturnWeightLogBywoid0(String woid);
}
