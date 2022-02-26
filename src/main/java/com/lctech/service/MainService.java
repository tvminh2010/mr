package com.lctech.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.dao.DetailCompDao;
import com.dao.ReceiptCompDao;
import com.dao.TurnDao;
import com.dao.WorkOrderDao;
import com.entity.DetailComp;
import com.entity.ReceiptComp;
import com.entity.Turn;
import com.entity.WorkOrder;


@Service
public class MainService {
	private static final Logger logger = Logger.getLogger(MainService.class);

	@Autowired
	private TurnDao tdao;
	@Autowired
	private ReceiptCompDao rcdao;
	@Autowired
	private DetailCompDao dcdao;
	
	@Autowired
	private WorkOrderDao wodao;
	@Autowired
	private PsWorkOrderService pssv;
	@Transactional
	public void saveOrUpdateImportReponse(ReceiptComp rc,ReceiptComp rcpending){
		try{
		 
  	       rcdao.save(rc);
  	      
  	       for(DetailComp dc : rc.getlDetailComp()){
 				if(dc.getQty()>0){
 					dcdao.save(dc);
 				}
  	       }
  	     rcdao.save(rcpending);
		}catch(Exception e){
			logger.info(e);
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		}
	

}
