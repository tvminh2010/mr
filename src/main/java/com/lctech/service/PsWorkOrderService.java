package com.lctech.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CloseTimeDao;
import com.dao.DetailCompDao;
import com.dao.PsProductDao;
import com.dao.ReceiptCompDao;
import com.dao.ReturnWeightLogDao;
import com.dao.WorkOrderDao;
import com.entity.CloseTime;
import com.entity.DetailComp;
import com.entity.PsProduct;
import com.entity.ReceiptComp;
import com.entity.WorkOrder;


@Service
public class PsWorkOrderService {
	
	@Autowired
	private WorkOrderDao wodao;
	@Autowired
	private ReceiptCompDao rcdao;
	@Autowired
	private DetailCompDao dcdao;
	@Autowired
	private PsProductDao psdao;
	 @Autowired
	 private  CloseTimeDao ctdao;
		@Autowired
		private ReturnWeightLogDao rwldao;
		
	
	public ReceiptComp getTotalWO(String woid){
		ReceiptComp rc = new ReceiptComp();
		WorkOrder wo = wodao.getWObyId(woid);
		
		List<PsProduct> lps = new ArrayList<PsProduct>();
		lps = psdao.getLPs(wo.getModel().getPt_part());
		List<DetailComp> ldc = new ArrayList<DetailComp>();
		
		for(PsProduct ps:lps){
		 DetailComp dc = new DetailComp();
		 
		 dc.setModel(ps.getPs_comp());
		 
		 dc.setPlan(ps.getPs_qty_per()*wo.getQty());
		 dc.setPs_qty_per(ps.getPs_qty_per());
		 
		 List<Object[][]> ltotal = wodao.getDetailTotal(wo.getId());
		 for(Object[] ob:ltotal){
			 if(ob[0].equals(ps.getPs_comp().getPt_part())){
				// DetailComp.TotalQty tt= new DetailComp.TotalQty();
				 dc.setTotalRequestSetup((Double)ob[1]);
				dc.setTotalRequestBS((Double)ob[2]);
				dc.setTotalResponse((Double)ob[3]);
				dc.setTotalReturn((Double)ob[4]);
				
			 }
		 }

		 ldc.add(dc);
		 
		}
		rc.setlDetailComp(ldc);
		rc.setWo(wo);
		return rc;
		
	}
	public ReceiptComp getReceiptUpdate(String rcid){
		ReceiptComp rc = new ReceiptComp();
		rc= rcdao.getbyId(rcid);
		
		List<PsProduct> lps = new ArrayList<PsProduct>();
		WorkOrder wo = wodao.getWObyId(rc.getWo().getId());
	
		lps = psdao.getLPs(wo.getModel().getPt_part());
		ReceiptComp rcbydate = new ReceiptComp();
		rcbydate = rcdao.getSetupBSbyDate(wo.getId());
		
		List<DetailComp> ldc = new ArrayList<DetailComp>();
		List<DetailComp> ldc1 = new ArrayList<DetailComp>();
	   // ldc1=  dcdao.getlistDetailCompbyreceiptid(woid);
	    ldc1 = dcdao.getlistDetailCompbytid(rcid);
	  /*  List<ReceiptComp> lrc =  rcdao.getbyIsWaitWOid(true,woid);
	  
	    if(!lrc.isEmpty() && lrc.size()>0){
	    	ReceiptComp rcwait = new ReceiptComp();
	    	rcwait =lrc.get(0);
	    	rc.setId(rcwait.getId());
	    	rc.setClosetime(rcwait.getClosetime());
	    }*/
	  
	    
		for(PsProduct ps:lps){
		 DetailComp dc = new DetailComp();
		 
		 dc.setModel(ps.getPs_comp());
		
		 dc.setPlan(ps.getPs_qty_per()*wo.getQty());
		 dc.setPs_qty_per(ps.getPs_qty_per());
		 
		 List<Object[][]> ltotal = wodao.getDetailTotal(wo.getId());
		 for(Object[] ob:ltotal){
			 if(ob[0].equals(ps.getPs_comp().getPt_part())){
				// DetailComp.TotalQty tt= new DetailComp.TotalQty();
				 dc.setTotalRequestSetup((Double)ob[1]);
				dc.setTotalRequestBS((Double)ob[2]);
				dc.setTotalResponse((Double)ob[3]);
				dc.setTotalReturn((Double)ob[4]);
				
			 }
		 }
		 if(!ldc1.isEmpty() && ldc1.size()>0){
			
		 for(DetailComp dc1 : ldc1){
			 if(dc1.getModel().getPt_part().equals(ps.getPs_comp().getPt_part())){
				 dc.setQty(dc1.getQty());
				 dc.setId(dc1.getId());
			 }
		 }
		 }
		 if(rcbydate!=null && rcbydate.getlDetailComp() !=null && rcbydate.getlDetailComp().size()>0){
			rc.setIsPending(true);
		 for(DetailComp dc2 : rcbydate.getlDetailComp()){
			 if(dc2.getModel().getPt_part().equals(ps.getPs_comp().getPt_part())){
				 dc.setQtyEarly(dc2.getQty());
				 
			 }
		 }}

		 ldc.add(dc);
		 
		}
		CloseTime lnt = ctdao.getNextTime(new Time(Calendar.getInstance().getTime().getTime()));
		if(rc.getClosetime()==null){
			rc.setClosetime(lnt);
		}
		rc.setlDetailComp(ldc);
		rc.setWo(wo);
		
		return rc;
		
	}
	public ReceiptComp getReceiptUpdatebyWo(String woid){
		ReceiptComp rc = new ReceiptComp();
		//rc= rcdao.getbyId(rcid);
		
		List<PsProduct> lps = new ArrayList<PsProduct>();
		WorkOrder wo = wodao.getWObyId(woid);
	
		lps = psdao.getLPs(wo.getModel().getPt_part());
		ReceiptComp rcbydate = new ReceiptComp();
		rcbydate = rcdao.getSetupBSbyDate(wo.getId());
		
		List<DetailComp> ldc = new ArrayList<DetailComp>();
		//List<DetailComp> ldc1 = new ArrayList<DetailComp>();
	   // ldc1=  dcdao.getlistDetailCompbyreceiptid(woid);
	//ldc1 = dcdao.getlistDetailCompbytid(rcid);
	  /*  List<ReceiptComp> lrc =  rcdao.getbyIsWaitWOid(true,woid);
	  
	    if(!lrc.isEmpty() && lrc.size()>0){
	    	ReceiptComp rcwait = new ReceiptComp();
	    	rcwait =lrc.get(0);
	    	rc.setId(rcwait.getId());
	    	rc.setClosetime(rcwait.getClosetime());
	    }*/
	  
	    
		for(PsProduct ps:lps){
		 DetailComp dc = new DetailComp();
		 
		 dc.setModel(ps.getPs_comp());
		
		 dc.setPlan(ps.getPs_qty_per()*wo.getQty());
		 dc.setPs_qty_per(ps.getPs_qty_per());
		 
		 List<Object[][]> ltotal = wodao.getDetailTotal(wo.getId());
		 for(Object[] ob:ltotal){
			 if(ob[0].equals(ps.getPs_comp().getPt_part())){
				// DetailComp.TotalQty tt= new DetailComp.TotalQty();
				 dc.setTotalRequestSetup((Double)ob[1]);
				dc.setTotalRequestBS((Double)ob[2]);
				dc.setTotalResponse((Double)ob[3]);
				dc.setTotalReturn((Double)ob[4]);
				
			 }
		 }
		 /*if(!ldc1.isEmpty() && ldc1.size()>0){
			
		 for(DetailComp dc1 : ldc1){
			 if(dc1.getModel().getPt_part().equals(ps.getPs_comp().getPt_part())){
				 dc.setQty(dc1.getQty());
				 dc.setId(dc1.getId());
			 }
		 }
		 }*/
		 if(rcbydate!=null && rcbydate.getlDetailComp() !=null && rcbydate.getlDetailComp().size()>0){
			rc.setIsPending(true);
		 for(DetailComp dc2 : rcbydate.getlDetailComp()){
			 if(dc2.getModel().getPt_part().equals(ps.getPs_comp().getPt_part())){
				 dc.setQtyEarly(dc2.getQty());
				 
			 }
		 }}

		 ldc.add(dc);
		 
		}
		CloseTime lnt = ctdao.getNextTime(new Time(Calendar.getInstance().getTime().getTime()));
		if(rc.getClosetime()==null){
			rc.setClosetime(lnt);
		}
		rc.setlDetailComp(ldc);
		rc.setWo(wo);
		
		return rc;
		
	}
	public ReceiptComp getReceiptUpdateHTGN(String rcid){
	
		//rc= rcdao.getbyId(rcid);
		
		List<PsProduct> lps = new ArrayList<PsProduct>();
		ReceiptComp rc =rcdao.getbyId(rcid);
	
		WorkOrder wo = wodao.getWObyId(rc.getWo().getId());
		
		lps = psdao.getLPs(wo.getModel().getPt_part());
		ReceiptComp rcbydate = new ReceiptComp();
		
		
		List<DetailComp> ldc = new ArrayList<DetailComp>();
		List<DetailComp> ldc1 = new ArrayList<DetailComp>();
	    ldc1=  dcdao.getlistDetailCompbytid(rcid);
	   
	  
	    
		for(PsProduct ps:lps){
		 DetailComp dc = new DetailComp();
		 
		 dc.setModel(ps.getPs_comp());
		
		 dc.setPlan(ps.getPs_qty_per()*wo.getQty());
		 dc.setPs_qty_per(ps.getPs_qty_per());
		 
		 List<Object[][]> ltotal = wodao.getDetailTotal(wo.getId());
		 for(Object[] ob:ltotal){
			 if(ob[0].equals(ps.getPs_comp().getPt_part())){
				// DetailComp.TotalQty tt= new DetailComp.TotalQty();
				 dc.setTotalRequestSetup((Double)ob[1]);
				dc.setTotalRequestBS((Double)ob[2]);
				dc.setTotalResponse((Double)ob[3]);
				dc.setTotalReturn((Double)ob[4]);
				
			 }
		 }
		 if(!ldc1.isEmpty() && ldc1.size()>0){
			
		 for(DetailComp dc1 : ldc1){
			 if(dc1.getModel().getPt_part().equals(ps.getPs_comp().getPt_part())){
				 dc.setQty(dc1.getQty());
				 dc.setId(dc1.getId());
			 }
		 }
		 }
	

		 ldc.add(dc);
		 
		}
		
		rc.setlDetailComp(ldc);
		rc.setWo(wo);
		return rc;
		
	}
	public ReceiptComp collectShareCoreWeight(String woid) {
		// TODO Auto-generated method stub
		List<Object[][]> lscw = rwldao.getReturnWeightLogBywoidOrderbyNVL(woid);
		ReceiptComp rc = getReceiptUpdatebyWo(woid);
		for(DetailComp dc : rc.getlDetailComp()) {
				for(Object[] obj : lscw) {
					String model = (String)obj[1];
					if(model.equalsIgnoreCase(dc.getModel().getPt_part())) {
						dc.setQty((Double)obj[2]);
					}
				}
	    }
		
		return rc;
	}
}
