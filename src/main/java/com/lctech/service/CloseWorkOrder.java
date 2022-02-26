package com.lctech.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;






import org.springframework.transaction.annotation.Transactional;

import com.dao.ReceiptCompDao;
import com.dao.WorkOrderDao;
import com.entity.CloseTime;
import com.entity.ReceiptComp;
import com.entity.Turn;
import com.entity.TypeComp;
import com.entity.WorkOrder;
@Service
public class CloseWorkOrder {

	private static final Logger logger = Logger.getLogger(CloseWorkOrder.class);

	
	@Autowired
	private ReceiptCompDao rcdao;
	@Autowired
	private WorkOrderDao wodao;
	@Autowired
	private ExportExcel exportExcel;
	@Transactional
	public void closeworkorder(CloseTime lnt){
		logger.info("CloseTime " + lnt.getName() + " time:" +lnt.getClosetime());
	    String turnid = setTurnDefault();
		//endscheduler
	    List<ReceiptComp> lrc = rcdao.getbyIsWaitCloseTimeCurrent(true, lnt);
		 Turn turn = new Turn(turnid);
			for(ReceiptComp rc:lrc){
				 WorkOrder wo = wodao.getWObyId(rc.getWo().getId());
				  if(wo.getStatus()==1){
		    	      rc.setType(TypeComp.RequestSetup);
			      }else{
			    	  rc.setType(TypeComp.RequestBS);
			      }
					  if(wo.getStatus()==1){
					  wo.setStatus(2);
				  }
			      rcdao.save(rc);
				  wodao.save(wo);
			}
		logger.info("export Excel turnid:" + turnid + " lnt:" + lnt.getName());

		exportExcel.exportTurnBS(turnid,lnt);
		exportExcel.exportTurnSetup(turnid,lnt);
		
		logger.info("update rc");

			for(ReceiptComp rc:lrc){
				rc.setIsPending(true);
				rc.setIsWait(false);
				rc.setTurn(turn);
			    rcdao.save(rc);
			    logger.info("update rc" + rc.getId() );
			}
	}
	public String setTurnDefault(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
      
        String turn = "T" + simpleDateFormat.format(new Date());
        return turn;
	}
}
