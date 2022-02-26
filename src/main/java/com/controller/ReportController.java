package com.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.DetailCompDao;
import com.dao.ReceiptCompDao;
import com.dao.WorkOrderDao;
import com.config.Config;
import com.entity.ReceiptComp;
import com.entity.Turn;
import com.entity.TypeComp;
import com.entity.WorkOrder;
import com.lctech.service.ExportExcel;
import com.lctech.service.ReportsServive;




@Controller

public class ReportController {
	private static final Logger logger = Logger.getLogger(ReportController.class);

	@Autowired
	private Config config;
	@Autowired
	private ReceiptCompDao rcdao;
	@Autowired
	private WorkOrderDao wodao;
	@Autowired
	private DetailCompDao dcdao;
	@Autowired
	private ExportExcel exportExcel;
	@Autowired
	private ReportsServive reportsServive;
	
	@RequestMapping(value="bywo",method = RequestMethod.GET)
	public String bywo(Model model,@RequestParam(value = "woname",required = false) String woname){
	       List<ReceiptComp> lrc = rcdao.getbywoname(woname);
	       model.addAttribute("woname", woname);
	       model.addAttribute("lrc", lrc);
	return "bywo";
	}
	@RequestMapping(value="bywo1",method = RequestMethod.GET)
	public String bywo1(Model model,@RequestParam(value = "woname",required = false) String woname,
			   @RequestParam(value = "error",required = false) Integer error){
		try{
		   if(woname != null){
		   WorkOrder wo = wodao.getWObyName(woname);
		 
		   if(wo != null && wo.getId() != null){
			   if(wo != null && wo.getStatus()==1){
				   model.addAttribute("error", 16);
					  return "bywo1";
				  }
			   String woid = wo.getId();
			   List<Object[]> lwo1 = dcdao.bcwo11(wo.getId());
			   model.addAttribute("lwo1", lwo1);
			   model.addAttribute("wo", wo);
			   
			   Turn t = dcdao.getTurnSetup(woid);
			   String turnid = t==null?"":t.getId();
			   List<Object[]> ls21 = dcdao.bcwo12Setup(woid, turnid);
			   List<Object[]> lb22 = dcdao.bcwo12BS(woid, turnid);
			   List<List<Object>> l2 = reportsServive.getAddListReponse(woname);
			   model.addAttribute("l2", l2);
			   model.addAttribute("ls21", ls21);
			   model.addAttribute("lb22", lb22);
			   model.addAttribute("woname", woname);
		   }else{
			   model.addAttribute("error", 17);
				return "bywo1";
		   }
	      // model.addAttribute("woname", woname);
		   }
	       return "bywo1";
		}catch(Exception e ){
			logger.debug(e);
			e.printStackTrace();
			model.addAttribute("error", 1);
			return "bywo1";
		}
	}
	@RequestMapping(value="bywo1excel",method = RequestMethod.GET)
	public void bywo1excel(Model model,@RequestParam(value = "woname",required = false) String woname,
			HttpSession session,HttpServletResponse response,HttpServletRequest request){
		
		   try {
			   
	      
	      
	       HSSFWorkbook workbook = exportExcel.bywo1excel(woname);
	       
	       response.setContentType("Content-Type: text/html");
	       response.setHeader("Content-disposition", "attachment; filename=WorkOrder.xls");
			workbook.write( response.getOutputStream() );
			 response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.debug(e);

			e.printStackTrace();
		}
	}
	@RequestMapping(value="bywoptdesc2",method = RequestMethod.GET)
	public String bywoptdesc2(Model model,
			@RequestParam(value = "page",required = false) Integer page,
			@RequestParam(value = "woname",required = false) String woname,
			@RequestParam(value = "startdate",required = false)@DateTimeFormat(pattern="dd/MM/yyyy")  Date startdate,
			@RequestParam(value = "enddate",required = false)@DateTimeFormat(pattern="dd/MM/yyyy")  Date enddate,
			@RequestParam(value = "item",required = false) String item,
			@RequestParam(value = "line",required = false) String line
			){
		if(page==null){
			page=1;
		}
		if(enddate==null){
			enddate= new Date(new java.util.Date().getTime());
		}
	       List<Object[]> lrc = dcdao.reportwo(startdate, enddate,page,woname,item,line);
	       List<Object[]> lrcgetrow = dcdao.reportwo(startdate, enddate,null,woname,item,line);
	       List<String> listPt_desc2 = dcdao.reportwoLDesc2( startdate,enddate,woname,item,line);
	       model.addAttribute("startdate", startdate);
	       model.addAttribute("woname", woname);
	       model.addAttribute("item", item);
	       model.addAttribute("enddate", enddate);
	       model.addAttribute("line", line);
	      
	       model.addAttribute("lrc", lrc);
	       model.addAttribute("page", page);
	       model.addAttribute("size", lrcgetrow.size()/config.getPageSize()<1?1:lrcgetrow.size()/config.getPageSize()+1);
			  model.addAttribute("pageSize", config.getPageSize());
	       model.addAttribute("listPt_desc2", listPt_desc2);
	return "bywoptdesc2";
	}
	@RequestMapping(value="bywoptdesc2export",method = RequestMethod.GET)
	public void bywoptdesc2export(Model model,
			HttpSession session,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value = "woname",required = false) String woname,
			@RequestParam(value = "startdate",required = false)@DateTimeFormat(pattern="dd/MM/yyyy")  Date startdate,
			@RequestParam(value = "enddate",required = false)@DateTimeFormat(pattern="dd/MM/yyyy")  Date enddate,
			
			@RequestParam(value = "item",required = false) String item,
			@RequestParam(value = "line",required = false) String line){
		   try {
			   List<Object[]> lrc = dcdao.reportwo(startdate, enddate,null,woname,item,line);
		       List<String> listPt_desc2 = dcdao.reportwoLDesc2( startdate,enddate,woname,item,line);
	       model.addAttribute("startdate", startdate);
	       model.addAttribute("enddate", enddate);
	       model.addAttribute("lrc", lrc);
	       model.addAttribute("listPt_desc2", listPt_desc2);
	       HSSFWorkbook workbook = exportExcel.exportWOByDesc2(listPt_desc2, lrc);
	       
	       response.setContentType("Content-Type: text/html");
	       response.setHeader("Content-disposition", "attachment; filename=WorkOrder.xls");
			workbook.write( response.getOutputStream() );
			 response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.debug(e);
			e.printStackTrace();
		}
	
	}

	@RequestMapping(value="bywoptdesc21",method = RequestMethod.GET)
	public String bywoptdesc21(Model model,
			@RequestParam(value = "page",required = false) Integer page,
			@RequestParam(value = "woname",required = false) String woname,
			@RequestParam(value = "startdate",required = false)@DateTimeFormat(pattern="dd/MM/yyyy") Date startdate,
			@RequestParam(value = "enddate",required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date enddate,
			@RequestParam(value = "item",required = false) String item,
			@RequestParam(value = "line",required = false) String line
			){
		if(page==null){
			page=1;
		}
		if(enddate==null){
			enddate= new Date(new java.util.Date().getTime());
		}
	       List<Object[]> lrc = dcdao.reportwo1(startdate, enddate,page,woname,item,line);
	       List<Object[]> lrcgetrow = dcdao.reportwo1(startdate, enddate,null,woname,item,line);
	      
	       model.addAttribute("startdate", startdate);
	       model.addAttribute("woname", woname);
	       model.addAttribute("item", item);
	       model.addAttribute("enddate", enddate);
	       model.addAttribute("line", line);
	      
	       model.addAttribute("lrc", lrc);
	       model.addAttribute("page", page);
	       model.addAttribute("size", lrcgetrow.size()/config.getPageSize()<1?1:lrcgetrow.size()/config.getPageSize()+1);
			  model.addAttribute("pageSize", config.getPageSize());
			  model.addAttribute("startRecord", config.getPageSize()*(page-1));
	return "bywoptdesc21";
	}
	@RequestMapping(value="bywoptdesc21export",method = RequestMethod.GET)
	public void bywoptdesc21export(Model model,
			HttpSession session,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value = "woname",required = false) String woname,
			@RequestParam(value = "startdate",required = false)@DateTimeFormat(pattern="dd/MM/yyyy")  Date startdate,
			@RequestParam(value = "enddate",required = false)@DateTimeFormat(pattern="dd/MM/yyyy")  Date enddate,
			
			@RequestParam(value = "item",required = false) String item,
			@RequestParam(value = "line",required = false) String line){
		   try {
			   List<Object[]> lrc = dcdao.reportwo1(startdate, enddate,null,woname,item,line);
		       
	       model.addAttribute("startdate", startdate);
	       model.addAttribute("enddate", enddate);
	       model.addAttribute("lrc", lrc);
	   
	       HSSFWorkbook workbook = exportExcel.exportWOByDesc21(lrc);
	       
	       response.setContentType("Content-Type: text/html");
	       response.setHeader("Content-disposition", "attachment; filename=WorkOrder.xls");
			workbook.write( response.getOutputStream() );
			 response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.debug(e);
			e.printStackTrace();
		}
	
	}
	@RequestMapping(value="byturn",method = RequestMethod.GET)
	public String byturn(Model model,@RequestParam(value = "name",required = false) String name){
		List<ReceiptComp> lrc = rcdao.getbyturn(name);
	       model.addAttribute("lrc", lrc);
	       model.addAttribute("name", name);
	return "byturn";
	}
	@RequestMapping(value="bc",method = RequestMethod.GET)
	public String bc(Model model,
			@RequestParam(value = "page",required = false) Integer page,
			@RequestParam(value = "type",required = false) TypeComp type){
		 if(page==null){
       	  page=1;
         }
		List<ReceiptComp> lrc = rcdao.getbytype(type, page);
	       model.addAttribute("lrc", lrc);
	          Long wosize = rcdao.getSize();
	         
	          
	          model.addAttribute("page", page);
			  model.addAttribute("size",wosize/config.getPageSize()<1?1:wosize/config.getPageSize()+1);
			  model.addAttribute("pageSize", config.getPageSize());
			  model.addAttribute("typesetup", TypeComp.RequestSetup);
			    model.addAttribute("typebs", TypeComp.RequestBS);
			    model.addAttribute("typereturn", TypeComp.Return);
			    model.addAttribute("typeresponse", TypeComp.Response);
	return "bc";
	}
	  @RequestMapping(value="bc/deletect",method = RequestMethod.GET)
	   public String deletect(Model model, @RequestParam(value = "id",required = false)String id)  {
		
		   ReceiptComp rc = rcdao.getbyId(id);
		   try{
		   logger.info("delrcid  deletect rcid= " + rc.getId());

		   rcdao.delete(rc);
		   }catch(Exception e){
			   logger.debug(e);
			   e.printStackTrace();
			   return "redirect:/bc?error=2";
		   }
		   
	      return "redirect:/bc?page=1";
	 }
}
