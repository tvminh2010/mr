package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.util.Comparator;

import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dao.CloseTimeDao;
import com.dao.DetailCompDao;
import com.dao.LineNoDao;
import com.dao.ProductDao;
import com.dao.ReceiptCompDao;
import com.dao.ReturnExcelDao;
import com.dao.TurnDao;
import com.dao.UserDao;
import com.dao.WorkOrderDao;
import com.entity.CloseTime;
import com.config.Config;
import com.entity.DetailComp;
import com.entity.LineNo;
import com.entity.Product;
import com.entity.ReceiptComp;
import com.entity.ReturnExcel;
import com.entity.Turn;
import com.entity.TypeComp;
import com.entity.User;
import com.entity.WorkOrder;
import com.lctech.service.CloseWorkOrder;
import com.lctech.service.ExportExcel;
import com.lctech.service.MainService;
import com.lctech.service.PsWorkOrderService;
import com.lctech.service.ImportDetailComp;
import com.lctech.service.ImportWorkOrder;
import com.lctech.util.utilMain;



@Controller
@RequestMapping({"/","/planNVL"})
public class MainController {
	private static final Logger logger = Logger.getLogger(MainController.class);
	@Autowired
	private WorkOrderDao wodao;
	@Autowired
	private ImportWorkOrder importWO;
	@Autowired
	private UserDao userdao;
	@Autowired
	private LineNoDao lndao;
	@Autowired
	private DetailCompDao dcdao;
	@Autowired
	private ProductDao pdao;
	@Autowired
	private ReceiptCompDao rcdao;
	@Autowired
	private ExportExcel exportExcel;
	@Autowired
	private TurnDao tdao;
	@Autowired
	private ImportDetailComp imDetailComp;
	@Autowired
	   private Config config;
	 @Autowired
	 private  CloseTimeDao ctdao;
	 @Autowired
		private PsWorkOrderService wosv;
	 @Autowired
	 private  CloseWorkOrder closewo;
	 
	 @Autowired
	 private MainService mainService;
	 @Autowired
	 private ReturnExcelDao rxdao;
	 private static List<ReceiptComp> lrc = new ArrayList<ReceiptComp>();
	 private static String turn = "";
	 private static  Boolean bs = false;
	 private static  Boolean setup = false;
	private utilMain utilm;
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid user and password!");
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.addObject("user", getPrincipal());
		model.setViewName("login");
		return model;
	}
	@RequestMapping(value = "/denied")
	public String denied() {
		return "denied";
	}
	@RequestMapping({"/error"})
	public String error(Model model){
		
		return "error";
   }

	  @RequestMapping(value="listycnvl",method = RequestMethod.GET)
	   public String listycnvl(Model model,@RequestParam(value = "msg",required = false) Integer msg)  {
		
			List<ReceiptComp> lrc1 = rcdao.getbyIsWait(true);
			List<ReceiptComp> lrc = new ArrayList<ReceiptComp>();
			for(ReceiptComp rc : lrc1){
				
					ReceiptComp rc1 = wosv.getReceiptUpdate(rc.getId());
					
					lrc.add(rc1);
				}
			 model.addAttribute("msg", msg);
			 model.addAttribute("lrc", lrc);
	      return "listycnvl";
	 }
	  @RequestMapping(value="closeturn",method = RequestMethod.GET)
	   public String closeturn(Model model,
			   @RequestParam(value = "error",required = false) Integer error,
			   @RequestParam(value = "msg",required = false) Integer msg)  {
		  try{
			  CloseTime lntc = ctdao.getNextTime();
			  closewo.closeworkorder(lntc);
		  }catch(Exception e){
			   logger.info(e.toString());
			  return "redirect:/listycnvl?error=3";
		  }
		 
	      return "redirect:/listycnvl?msg=1";
	 }
	
	  @RequestMapping(method = RequestMethod.GET)
	   public String planNVL(Model model,
			   @RequestParam(value = "startdate",required = false)@DateTimeFormat(pattern="dd/MM/yyyy")  Date startdate,
				@RequestParam(value = "enddate",required = false)@DateTimeFormat(pattern="dd/MM/yyyy")  Date enddate,
			   @RequestParam(value = "page",required = false) Integer page,
			   @RequestParam(value = "id",required = false) String id,
			   @RequestParam(value = "error",required = false) Integer error,
			   @RequestParam(value = "msg",required = false) Integer msg,
			   @RequestParam(value = "ms",required = false) String ms,
			   @RequestParam(value = "search",required = false) Boolean search,
			   @RequestParam(value = "woname",required = false) String woname,
			 
			   @RequestParam(value = "item",required = false) String item,
			   @RequestParam(value = "line",required = false) String line
			   )  {
		  if(page == null){
			  page=1;
		  }
		  WorkOrder wo = new WorkOrder();
		  if(id!=null){
			  wo = wodao.getWObyId(id);
		  }
		  if(enddate==null){
			  enddate= new Date(new java.util.Date().getTime());
		  }
		  if(startdate==null){
			  Calendar cal = Calendar.getInstance();
					  cal.add(Calendar.DATE, -30);
					  startdate = new Date(cal.getTime().getTime());
		  }
		  model.addAttribute("wo", wo);
		  List<WorkOrder> lwo = wodao.getListPage(startdate,enddate,page,woname,line,item);
		  List<WorkOrder> lwogetsize = wodao.getListPage(startdate,enddate,null,woname,line,item);
		  int wosize = lwogetsize.size();
		  model.addAttribute("page", page);
		  model.addAttribute("lwo", lwo);
		  model.addAttribute("error", error);
		  model.addAttribute("msg", msg);
		  model.addAttribute("ms", ms);
		  model.addAttribute("size",wosize/config.getPageSize()<1?1:wosize/config.getPageSize()+1);
		  model.addAttribute("pageSize", config.getPageSize());
		  model.addAttribute("listline", lndao.getlistLineNo());
		  model.addAttribute("lp", pdao.listProductV_CNC());
		  model.addAttribute("search", search);
		  model.addAttribute("woname", woname);
		  model.addAttribute("line", line);
		  model.addAttribute("item", item);
		  model.addAttribute("startdate", startdate);
		  model.addAttribute("enddate", enddate);
	      return "planNVL";
	 }
	  @RequestMapping(value="/planNVL",method = RequestMethod.POST)
	   public String showPlan(Model model,
			   @ModelAttribute("wo")WorkOrder wo
			 )  {
		try{
		wo.setName(wo.getName().trim());
		 Product p = pdao.getProductByDesc1(wo.getModel().getPt_desc1());
		 if(p==null){
			 return "redirect:/planNVL?error=11";
		 }
		 wo.setModel(p);
		 LineNo ln = lndao.getLineNoName(wo.getLine().getName());
		 if(ln==null)
		 {
			 return "redirect:/planNVL?error=12";
			 
		 }
		 wo.setLine(ln);
		if(wo.getId()==null || wo.getId()=="" ){
			WorkOrder wocheck = wodao.getWObyName(wo.getName());
			if(wocheck!=null && wocheck.getId()!=null ){
				logger.info("wocheck loi");
				return "redirect:/planNVL?error=1";
			}
			  String woid = UUID.randomUUID().toString();
			  wo.setId(woid);
			  
			  
			  wo.setStatus(1);
		}else{
			WorkOrder wocheck1 = wodao.checkWObyName(wo.getName(),wo.getId());
			if(wocheck1!=null && wocheck1.getId() != null){
				logger.info("wocheck1 loi");
				return "redirect:/planNVL?error=1";
			}
		}
		  wo.setCreatedate(new java.sql.Date(new Date().getTime()));
		  User us = userdao.getUserByName(getPrincipal());
		
		  wo.setUs(us);
		  wodao.save(wo);
		return "redirect:/planNVL?msg=1";
		}catch(Exception e){
			 logger.info(e.toString());
			return "redirect:/planNVL?error=1";
		}
		  
	  
		 
	 }
	  @RequestMapping(value="deleteplan",method = RequestMethod.GET)
	   public String deletect(Model model, @RequestParam(value = "id",required = false)String id)  {
		   logger.info("delete closetime");
		   WorkOrder closetime = wodao.getWObyId(id);
		   try{
		   wodao.delete(closetime);
		   }catch(Exception e){
			    logger.info(e.toString());
			   return "redirect:/planNVL?error=2";
		   }
		   
	      return "redirect:/planNVL?msg=1";
	 }
	   @RequestMapping(value="deleteplanall",method = RequestMethod.GET)
	   public String deletectall(Model model, @RequestParam(value = "lid",required = false)String lid)  {
		   logger.info(lid);
		   try{
			   wodao.deletelist(lid);
		   }catch(Exception e){
			    logger.info(e.toString());
			   return "redirect:/planNVL?error=2";
		   }
		  
		   
	      return "redirect:/planNVL?msg=1";
	 }
	  @RequestMapping(value="/importPlan",method = RequestMethod.POST)
	   public String importPlan(Model model,
			   @RequestParam("excelfile") MultipartFile excelfile
			 )  {
		  WorkOrder wo = new WorkOrder();
		try{
		  List<WorkOrder> lwo = importWO.readExcel(excelfile);
		   wo =  wodao.saveList(lwo);
		   if(wo!=null && wo.getId()!=null){
			   return "redirect:/planNVL?error=18";
			}
		 }catch(Exception e){
			  logger.info(e.toString());
			  model.addAttribute("ms",wo.getName());
			   
			 return "redirect:/planNVL?error=18";
		  }
	  	return "redirect:/planNVL?msg=1";
	 }
  
   @RequestMapping(value="setting",method = RequestMethod.GET)
	   public String setting(Model model)  {
	      return "setting";
	 }
   @RequestMapping(value="cowo",method = RequestMethod.GET)
   public String cowo(Model model,
		   @RequestParam(value = "error",required = false) Integer error,
		   @RequestParam(value = "msg",required = false) Integer msg)  {
	   
	  List<String> loing = wodao.getCowo(true);
	  List<String> lcing = wodao.getCowo(false);
	    model.addAttribute("loing",loing);
	    model.addAttribute("lcing",lcing);
	    model.addAttribute("msg",msg);
	    model.addAttribute("error",error);
      return "cowo";
 }
   @RequestMapping(value="owo",method = RequestMethod.POST)
   public String owo(ModelMap model,@RequestParam(value = "namewo",required = true) String name)  {
	   
	   try{
	   WorkOrder wo = wodao.getWObyName(name);
	  if(wo.getStatus()>3){
		  return "redirect:cowo?error=14";
	  }
	   wo.setStatus(2);
	   wodao.save(wo);
	   }catch(Exception e){
		     return "redirect:cowo?error=1";
	   }
      return "redirect:cowo?msg=1";
 }
   @RequestMapping(value="cwo",method = RequestMethod.POST)
   public String cwo(ModelMap model,@RequestParam(value = "namewo",required = true) String name)  {
	   
	   try{
	   WorkOrder wo = wodao.getWObyName(name);
	   if(wo.getStatus()==3 || wo.getStatus()==1){
			  return "redirect:cowo?error=15";
		  }
	   wo.setStatus(3);
	   wodao.save(wo);
	   }catch(Exception e){
		   return "redirect:cowo?error=1"; 
	   }
      return "redirect:cowo?msg=1";
 }
   @RequestMapping(value="baocao",method = RequestMethod.GET)
	   public String baocao(ModelMap model)  {
	      return "baocao";
	 }
  
   @RequestMapping(value ="turnexcel",method = RequestMethod.GET)
   public String turnexcel(Model model,@RequestParam(value = "page",required = false) Integer page,
		   @RequestParam(value = "error",required = false) Integer error,
		   @RequestParam(value = "msg",required = false) Integer msg)  {
	   logger.info("turnexcel");
	   if(page==null){
		   page=1;
	   }
	   
	   List<Turn> lturn = tdao.getlinkdownload(page);
	  // logger.info(lturn);
	   //lturn.sort(Comparator.comparing());
	 /*  lturn.sort(
			      (Turn h1, Turn h2) -> new Boolean(h1.getDownloadedbs() && h1.getDownloadedsetup())
          		.compareTo(new Boolean(h2.getDownloadedbs() &&  h2.getDownloadedsetup())));*/
	  /* Collections.sort(lturn, new Comparator<Turn>() {
	        @Override
	        public int compare(Turn h1, Turn h2) {
	            return new Boolean(h1.getDownloadedbs() && h1.getDownloadedsetup())
	            		.compareTo(new Boolean(h2.getDownloadedbs() &&  h2.getDownloadedsetup())) ;
	        }
	    });*/
	   //logger.info(lturn);
	   Long tsize= tdao.getSize();
	   model.addAttribute("lturn", lturn);
	
	    model.addAttribute("page",page);
	    model.addAttribute("size", tsize/config.getPageSize()<1?1:tsize/config.getPageSize()+1);
		  model.addAttribute("pageSize", config.getPageSize());
      return "turnexcel";
 }
   @RequestMapping(value ="returnexcel",method = RequestMethod.GET)
   public String returnexcel(Model model,@RequestParam(value = "page",required = false) Integer page,
		   @RequestParam(value = "error",required = false) Integer error,
		   @RequestParam(value = "msg",required = false) Integer msg)  {
	   logger.info("returnexcel");
	   if(page==null){
		   page=1;
	   }
	   
	   List<ReturnExcel> lrturn = rxdao.getList(page);
	  // logger.info(lturn);
	
	  // logger.info(lturn);
	   Long tsize= rxdao.getSize();
	   model.addAttribute("lrturn", lrturn);
	
	    model.addAttribute("page",page);
	    model.addAttribute("size", tsize/config.getPageSize()<1?1:tsize/config.getPageSize()+1);
		  model.addAttribute("pageSize", config.getPageSize());
      return "returnexcel";
 }
   @RequestMapping(value="turnexcel/deletectall",method = RequestMethod.GET)
   public String turnexceldeletectall(Model model, @RequestParam(value = "lid",required = false)String lid)  {
	   logger.info("delete turn excel");
	   try{
		   List<String> ltid = utilm.getLidString(lid);
		   for(String id : ltid){
			   Turn t = tdao.getByid(id);
		   tdao.delete(t);
		   }
	   }catch(Exception e){
		    logger.info(e.toString());
		   return "redirect:/turnexcel?error=2";
	   }
	   return "redirect:/turnexcel?msg=1";
   }
   @RequestMapping(value="turnexcel/deletect",method = RequestMethod.GET)
   public String turnexceldeletect(Model model, @RequestParam(value = "id",required = false)String id)  {
	   logger.info("delete turn excel");
	   try{
		   
		Turn t = tdao.getByid(id);
		   tdao.delete(t);
		   
	   }catch(Exception e){
		    logger.info(e.toString());
		   return "redirect:/turnexcel?error=2";
	   }
	   return "redirect:/turnexcel?msg=1";
   }
   @RequestMapping(value="giaonhan",method = RequestMethod.GET)
   public String giaonhan(Model model,@RequestParam(value="id",required =false) String id
		   ,
		   @RequestParam(value = "error",required = false) Integer error,
		   @RequestParam(value = "msg",required = false) String msg)  {
	 	logger.info("giao nhan");
        ReceiptComp rc = new ReceiptComp();
        
        if(id!=null){
        	
        	rc = wosv.getReceiptUpdateHTGN(id);
        }
        model.addAttribute("msg", msg);
        model.addAttribute("error", error);
        model.addAttribute("rc", rc);
	    model.addAttribute("listline", lndao.getlistLineNo());
	    model.addAttribute("type", TypeComp.Response);
      return "giaonhan";
 }
   @RequestMapping(value="hoantra",method = RequestMethod.GET)
   public String hoantra(Model model,@RequestParam(value="id",required =false) String id
		   ,
		   @RequestParam(value = "error",required = false) Integer error,
		   @RequestParam(value = "msg",required = false) Integer msg)  {
   logger.info("giao nhan");
   ReceiptComp rc = new ReceiptComp();
   if(id!=null){
	 
 
       	rc = wosv.getReceiptUpdateHTGN(id);
       
   }
   model.addAttribute("rc", rc);
   model.addAttribute("msg", msg);
   model.addAttribute("error", error);
    model.addAttribute("listline", lndao.getlistLineNo());
    model.addAttribute("type", TypeComp.Return);
 return "hoantra";
 }
   @RequestMapping(value="giaonhanexcel",method = RequestMethod.POST)
	   public String giaonhanexcel(Model model,@RequestParam("excelfile") MultipartFile excelfile)  {
	  logger.info("post giao nhan save");
	     try{
	   
	  
	    	 
	    	    String fileFrags = excelfile.getOriginalFilename();
	    	    // T201708030855_RequestSetup
				  // T201807130430_RequestBS
	    	    logger.info(excelfile.getOriginalFilename().length());
				   if(excelfile.getOriginalFilename().contentEquals("RequestSetup")) {
				            if(excelfile.getOriginalFilename().length() != 27)  {
				            	return "redirect:giaonhan?error=19";
				            }
				   }else if(excelfile.getOriginalFilename().contentEquals("RequestBS")){
					   if(excelfile.getOriginalFilename().length() != 24)  {
			            	return "redirect:giaonhan?error=19";
			            }
				   }
	    	    //save excel
	    	 
	    	    String turn = fileFrags.substring(0, fileFrags.indexOf("_"));
	    	
	    lrc = imDetailComp.readExcel(excelfile);
	    if(lrc !=null && lrc.size()==1 && lrc.get(0).getNote()=="1" ) {
	    	 return "redirect:giaonhan?error=9&msg="+lrc.get(0).getId();
	    }
	    for(ReceiptComp rc:lrc){
		    logger.info("wo.id:" + rc.getWo().getId() + " wo name : "+ rc.getWo().getName());

	    
	    }
	    model.addAttribute("lrc", lrc);
	    bs =  fileFrags.contains("RequestBS");
	     setup =  fileFrags.contains("RequestSetup");
	    model.addAttribute("lrc", lrc);
	    model.addAttribute("turn", turn);
	    if(lrc!=null && lrc.size()>0){
	   // logger.info("save giao nhan");
	    Turn t = tdao.getByid(turn);
		   Log.info("bs:" + bs + "st:" + setup);

	    if(bs){
	    	 t.setDownloadedbs(bs);
	    }else{
	   
	         t.setDownloadedsetup(setup);
	    }
	    	for(ReceiptComp rc:lrc){
	    		  ReceiptComp rcpending = new ReceiptComp();
	    		  rcpending = rcdao.getbyturnTypewo(t.getId(), TypeComp.Response, rc.getWo().getId());
	    		  if(rcpending != null && rcpending.getId() !=null) {
	    			  rc.setId(rcpending.getId());
	    			  //rcdao.save(rc);
	    			  for(DetailComp de : rc.getlDetailComp()) {
	    				  for(DetailComp de1 : rcpending.getlDetailComp()) {
	    					  if(de1.getModel().getPt_part().equalsIgnoreCase(de.getModel().getPt_part())) {
	    						  de.setId(de1.getId());
	    						  //dcdao.save(de);
	    					  }
	    				  }
	    			  }
	    			  for(DetailComp de : rc.getlDetailComp()) {
	    				  if(de.getId()==null) 
	    					  {
	    					     de.setId(UUID.randomUUID().toString());
	    					     de.setReceipt(rcpending);
	    					     dcdao.save(de);
	    					  }else {
	    						  dcdao.update(de);
	    					  }
	    					  
	    						
	    			  }
	    		  }else {
	    		if(bs){
	    			 rcpending = rcdao.getPending(rc.getWo().getId(),t,TypeComp.RequestBS);
	    		}else{
	    			rcpending = rcdao.getPending(rc.getWo().getId(),t,TypeComp.RequestSetup);
	    		}
	    		//  Log.info("rcpending" + rcpending.toString());
	    		  if(rcpending!=null && rcpending.getId()!=null){
	    		   rcpending.setIsPending(false);
	    		   rc.setTurn(t);
	    		   
	    		   mainService.saveOrUpdateImportReponse(rc, rcpending);
	    	
	    		  }
	    		/*  else{
	    			  return "redirect:giaonhan?error=7";
	    		  }*/
	    		  //find to update
	    	
	    		  }
	    	}
	    	lrc.clear();
	     
	    	 tdao.update(t);
	     return "redirect:giaonhan?msg=1";
	    }else{
	    	 return "redirect:giaonhan?error=5";
	    }
	    }catch(Exception e){
	    	e.printStackTrace();
	    	 logger.info(e);
	    	return "redirect:giaonhan?error=4";
	    }
	   
	  
	      
	 }
   @RequestMapping(value="giaonhanexcelshow",method = RequestMethod.POST)
   public String giaonhanexcelshow(Model model,@RequestParam("excelfile") MultipartFile excelfile)  {
  logger.info("post giao nhan");
     try{
   
    String fileFrags = excelfile.getOriginalFilename();
   
    //save excel
 
    String turn = fileFrags.substring(0, fileFrags.indexOf("_"));
    
     bs =  fileFrags.contains("RequestBS");
     setup =  fileFrags.contains("RequestSetup");
    lrc = imDetailComp.readExcel(excelfile);
    model.addAttribute("lrc", lrc);
    model.addAttribute("turn", turn);
   /* if(lrc!=null && lrc.size()>0){
    logger.info("save giao nhan");
    Turn t = tdao.getByid(turn);
    if(bs){
    	 t.setDownloadedbs(bs);
    }else{
   
         t.setDownloadedsetup(setup);
    }
   
    	for(ReceiptComp rc:lrc){
    		ReceiptComp rcpending = new ReceiptComp();
    		if(bs){
    			 rcpending = rcdao.getPending(rc.getWo().getId(),t,TypeComp.RequestBS);
    		}else{
    			rcpending = rcdao.getPending(rc.getWo().getId(),t,TypeComp.RequestSetup);
    		}
    		  
    		  if(rcpending!=null && rcpending.getId()!=null){
    		   rcpending.setIsPending(false);
    		   rc.setTurn(t);
    		   
    		   mainService.saveOrUpdateImportReponse(rc, rcpending);
    	
    		  }else{
    			  return "redirect:giaonhan?error=7";
    		  }
    	}
    	lrc.clear();
     
    	 tdao.save(t);*/
     return "giaonhan";
 
    }catch(Exception e){
    	 logger.info(e.toString());
    	return "redirect:giaonhan?error=4";
    }
   
  
      
 }
   @RequestMapping(value="giaonhan",method = RequestMethod.POST)
   public String giaonhanPost(Model model,
		   @ModelAttribute("rc")ReceiptComp rc)  {
	      try{
	      if(rc.getId()!=null &&  rc.getId()!="null" ){
		  WorkOrder wo = wodao.getWObyId(rc.getWo().getId());
		
		  
		  User us = userdao.getUserByName(getPrincipal());
	
		  rc.setWo(wo);
		  
		  rc.setUs(us);
		 
		  rc.setDate(new Timestamp(new java.util.Date().getTime()));
		  rc.setType(TypeComp.Response);
		 
			 
		  
		 
		  rcdao.save(rc);
		 
		  for(DetailComp dc:rc.getlDetailComp()){
			   //if(dc.getQty() != null && dc.getQty()>0){
			  if(dc.getId()==null || dc.getId().equalsIgnoreCase("") || dc.getId()=="null" ){
			  String dcid = UUID.randomUUID().toString();
			  dc.setId(dcid);
			  }
			  dc.setReceipt(new ReceiptComp(rc.getId()));
			  dc.setModel(pdao.getProductById(dc.getModel().getPt_part()));
			  dc.setQty(dc.getQty() == null?0:dc.getQty());
			  dcdao.save(dc);
			   
			  // }
			   
		  }
		
	      //return "yeucauNVL"; ok
	    	  }else{
	    		  return "redirect:giaonhan?error=7";
	    	  }
		  return "redirect:giaonhan?msg=1";
		 
	      }catch(Exception e){
	    	   logger.info(e.toString());
	    	  return "redirect:giaonhan?error=4";
	      }
 }
 
   @RequestMapping(value="hoantra",method = RequestMethod.POST)
   public String hoantraPost(Model model,
		   @ModelAttribute("rc")ReceiptComp rc)  {
	 
	   try{
		  WorkOrder wo = wodao.getWObyId(rc.getWo().getId());
		  User us = userdao.getUserByName(getPrincipal());
	
		  rc.setWo(wo);
		  rc.setUs(us);
		 rc.setType(TypeComp.Return);
		  rc.setDate(new Timestamp(new java.util.Date().getTime()));
		 
		  if(rc.getId()==null || rc.getId().equalsIgnoreCase("") || rc.getId()=="null" ){
			  //update
			  String id = UUID.randomUUID().toString();
			  rc.setId(id);
		  }
		  
		  rcdao.save(rc);
		  for(DetailComp dc:rc.getlDetailComp()){
			   //if(dc.getQty() != null && dc.getQty()>0){
			  if(dc.getId()==null || dc.getId().equalsIgnoreCase("") || dc.getId()=="null" ){
			  String dcid = UUID.randomUUID().toString();
			  dc.setId(dcid);
			  }
			  dc.setReceipt(new ReceiptComp(rc.getId()));
			  dc.setModel(pdao.getProductById(dc.getModel().getPt_part()));
			  dc.setQty(dc.getQty() == null?0:dc.getQty());
			  dcdao.save(dc);
			   
			  // }
			   
		  }
		
	      //return "yeucauNVL"; ok
		  
		  return "redirect:hoantra?msg=1";
	   }catch(Exception e){
		    logger.info(e.toString());
		   return "redirect:hoantra?error=4";
	   }
 }
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
/*	@RequestMapping(value = "getwo", method = RequestMethod.GET)
	public String  getwo(Model model,@RequestParam(value = "page",required = false) Integer page){
		logger.info("getwo");
		List<Object>  lo = new ArrayList<Object>();
		List<WorkOrder> lwo = new ArrayList<WorkOrder>();
	    lwo = wodao.getListPage( page);
	    Long wosize = wodao.getSize();
	    model.addAttribute("lwo",lwo);
	    model.addAttribute("page",page);
	    model.addAttribute("size", wosize/config.getPageSize());
		  model.addAttribute("pageSize", config.getPageSize());
		return "turnexcel";
	}*/
	@RequestMapping(value="downloadLogFile",method = RequestMethod.GET)
	public void getLogFile(HttpSession session,HttpServletResponse response,
			 @RequestParam(value = "filename",required = false) String filename
			 ,	 @RequestParam(value = "type",required = false) Byte type) throws Exception {
		logger.info("download" + type);
	    try {
	    	String filePathToBeServed= "";
	    	if(type ==1) {
		         filePathToBeServed = config.getDataExcelreponse()+ filename +".xls";
	    	}else if(type ==2){
		         filePathToBeServed = config.getDataExcelreturn() + filename +".xlsx";
	    	}else {
	    		filePathToBeServed = config.getDataExcel() + filename +".xls";
	    	}
	        File fileToDownload = new File(filePathToBeServed);
	        InputStream inputStream = new FileInputStream(fileToDownload);
	        response.setContentType("application/force-download");
	        response.setHeader("Content-Disposition", "attachment; filename="+ filename +".xls"); 
	        IOUtils.copy(inputStream, response.getOutputStream());
	        response.flushBuffer();
	        inputStream.close();
	    } catch (Exception e){
	    	logger.info("Request could not be completed at this moment. Please try again.");
	         logger.info(e.toString());
	    }

	}
	@RequestMapping(value="downloadLogFileTemp",method = RequestMethod.GET)
	public void getLogFileTemp(HttpSession session,HttpServletResponse response,HttpServletRequest request,
			 @RequestParam(value = "filename",required = false) String filename) throws Exception {
		logger.info("download" );
	    try {
	    	ServletContext context = request.getServletContext();
	        String appPath = context.getRealPath("");
	        
	        String filePathToBeServed = appPath +"/data/" + filename ;
	        File fileToDownload = new File(filePathToBeServed);
	        InputStream inputStream = new FileInputStream(fileToDownload);
	        response.setContentType("application/force-download");
	        response.setHeader("Content-Disposition", "attachment; filename="+ filename ); 
	        IOUtils.copy(inputStream, response.getOutputStream());
	        response.flushBuffer();
	        inputStream.close();
	    } catch (Exception e){
	    	logger.info("Request could not be completed at this moment. Please try again.");
	         logger.info(e.toString());
	    }

	}
}
