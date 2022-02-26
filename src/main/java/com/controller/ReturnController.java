package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.dao.CoreWeightDao;
import com.dao.DetailCompDao;
import com.dao.LineNoDao;
import com.dao.ProductDao;
import com.dao.ReceiptCompDao;
import com.dao.ReturnExcelDao;
import com.dao.ReturnWeightLogDao;
import com.dao.TurnDao;
import com.dao.UserDao;
import com.dao.WorkOrderDao;
import com.entity.CloseTime;
import com.config.Config;
import com.config.DBPosgressConnection;
import com.entity.DetailComp;
import com.entity.LineNo;
import com.entity.Product;
import com.entity.ReceiptComp;
import com.entity.ReturnExcel;
import com.entity.ReturnWeightLog;
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
@RequestMapping({"/return","/return/input"})
public class ReturnController {
	private static final Logger logger = Logger.getLogger(ReturnController.class);
	@Autowired
	private WorkOrderDao wodao;
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
	   private Config config;
	 @Autowired
		private PsWorkOrderService wosv;
	 @Autowired
	 private ReturnExcelDao rxdao;
		@Autowired
		private ReturnWeightLogDao rwldao;
		@Autowired
		private CoreWeightDao crdao;

   @RequestMapping(value="deletecoreweightlog",method = RequestMethod.GET)
   public String deleteReturn(Model model,@RequestParam(value="id",required =false) Long id,
		            @RequestParam(value = "page",required = false) Integer page,
				   @RequestParam(value="woname",required =false) String woname,
				   @RequestParam(value="item",required =false) String item,
				   @RequestParam(value="serialno",required =false) String serialno
		  )  {
   logger.info("delete return");
  if(id!=null) {
	   rwldao.delete(id);
  }
  
 return "redirect:listreturn?page="+page+"&woname="+woname+"&item="+item+"&serialno=+serialno";
 }
   @RequestMapping(method = RequestMethod.GET)
   public String hoantra(Model model,@RequestParam(value="id",required =false) String id
		   ,
		   @RequestParam(value = "error",required = false) Integer error,
		   @RequestParam(value = "msg",required = false) Integer msg,
		   @RequestParam(value = "filename",required = false) String filename)  {
   logger.info("return");
   ReceiptComp rc = new ReceiptComp();
   if(id!=null){
	
 
       	rc = wosv.getReceiptUpdateHTGN(id);
       
   }
	List<Object[]> tt = crdao.getTypeCore();
	   model.addAttribute("tt", tt);
   model.addAttribute("rc", rc);
   model.addAttribute("rc", rc);
   model.addAttribute("msg", msg);
   model.addAttribute("filename", filename);
   model.addAttribute("error", error);
    model.addAttribute("listline", lndao.getlistLineNo());
    model.addAttribute("type", TypeComp.Return);
 return "return";
 }
   @RequestMapping(value="listreturn",method = RequestMethod.GET)
   public String corewightlog(Model model,@RequestParam(value="id",required =false) String id,
		   @RequestParam(value="woname",required =false) String woname,
		   @RequestParam(value="item",required =false) String item,
		   @RequestParam(value="serialno",required =false) String serialno,
		   @RequestParam(value = "page",required = false) Integer page,
		   @RequestParam(value = "msg",required = false) Integer msg)  {
   logger.info("list return log");
    if(page == null) page = 1;
   List<ReturnWeightLog> lrw = rwldao.getList(page,woname,item,serialno);
   Long returnsize  = rwldao.countAll(woname,item,serialno);
   model.addAttribute("size",returnsize/config.getPageSize()<1?1:returnsize/config.getPageSize()+1);
	  model.addAttribute("pageSize", config.getPageSize());
	  model.addAttribute("page", page);
	  model.addAttribute("woname", woname);
	  model.addAttribute("item", item);
	  model.addAttribute("serialno", serialno);
	  model.addAttribute("lrw", lrw);
 
 return "listreturn";
 }
   
   
 
   @RequestMapping(value="return",method = RequestMethod.POST)
   public String hoantraPost(HttpServletRequest req,HttpSession session,HttpServletResponse response,Model model,
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
		  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
	      
	     
		    String filename =  "RETURN"+"_"+rc.getWo().getName() +"_"+simpleDateFormat.format(new Date());
		    
		   XSSFWorkbook workbook = exportExcel.exportReturn(rc.getWo(),req);
		    try {
	        	FileOutputStream fos = new FileOutputStream(config.getDataExcelreturn() + filename +".xlsx");
				workbook.write(fos);
				
				ReturnExcel rx = new ReturnExcel();
				rx.setPathexcel(filename);
				rx.setWoid(rc.getWo().getId());
				rx.setCreatedate(new Timestamp(new Date().getTime()));
				rx.setLine(rc.getWo().getLine().getName());
				rx.setModel(rc.getWo().getModel().getPt_part());
				rx.setWoname(rc.getWo().getName());
				rxdao.save(rx);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.info(e);
				e.printStackTrace();
			}
		   /* try {
		        String filePathToBeServed = config.getDataExcel_return() + filename +".xls";
		        File fileToDownload = new File(filePathToBeServed);
		        InputStream inputStream = new FileInputStream(fileToDownload);
		        response.setContentType("application/force-download");
		        response.setHeader("Content-Disposition", "attachment; filename="+ filename +".xls"); 
		        IOUtils.copy(inputStream, response.getOutputStream());
		        response.flushBuffer();
		        inputStream.close();
		    } catch (Exception e){
		    	logger.info("Request could not be completed at this moment. Please try again.");
		        e.printStackTrace();
		    }*/
		  return "redirect:/return/input?msg=1&filename="+filename;
	   }catch(Exception e){
			e.printStackTrace();
		   logger.error(e);
		   return "redirect:/return/input?error=9";
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

	@RequestMapping(value="downloadLogFile",method = RequestMethod.GET)
	public void getLogFile(HttpSession session,HttpServletResponse response,
			 @RequestParam(value = "filename",required = false) String filename
			 ,	 @RequestParam(value = "type",required = false) Byte type) throws Exception {
		logger.info("download");
	    try {
	        String filePathToBeServed ;
	    	if(type ==1) {
		         filePathToBeServed = config.getDataExcelreponse()+ filename +".xls";
			        response.setHeader("Content-Disposition", "attachment; filename="+ filename +".xls"); 

	    	}else if(type ==2){
		         filePathToBeServed = config.getDataExcelreturn() + filename +".xlsx";
			        response.setHeader("Content-Disposition", "attachment; filename="+ filename +".xlsx"); 

	    	}else {
	    		filePathToBeServed = config.getDataExcel() + filename +".xls";
		        response.setHeader("Content-Disposition", "attachment; filename="+ filename +".xls"); 

	    	}
	        File fileToDownload = new File(filePathToBeServed);
	        InputStream inputStream = new FileInputStream(fileToDownload);
	        response.setContentType("application/force-download");
	        IOUtils.copy(inputStream, response.getOutputStream());
	        response.flushBuffer();
	        inputStream.close();
	    } catch (Exception e){
	    	logger.info("Request could not be completed at this moment. Please try again.");
	    	logger.info(e);
	        e.printStackTrace();
	    }

	}
	@RequestMapping(value="downloadLogFileTemp",method = RequestMethod.GET)
	public void getLogFileTemp(HttpSession session,HttpServletResponse response,HttpServletRequest request,
			 @RequestParam(value = "filename",required = false) String filename) throws Exception {
		logger.info("download");
	    try {
	    	ServletContext context = request.getServletContext();
	        String appPath = context.getRealPath("");
	        
	        String filePathToBeServed = appPath +"/data/" + filename;
	        File fileToDownload = new File(filePathToBeServed);
	        InputStream inputStream = new FileInputStream(fileToDownload);
	        response.setContentType("application/force-download");
	        response.setHeader("Content-Disposition", "attachment; filename="+ filename ); 
	        IOUtils.copy(inputStream, response.getOutputStream());
	        response.flushBuffer();
	        inputStream.close();
	    } catch (Exception e){
	    	logger.debug(e);
	    	logger.info("Request could not be completed at this moment. Please try again.");
	        e.printStackTrace();
	    }

	}
}
