package com.controller;

import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.PrinterName;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.config.Config;
import com.config.DBPosgressConnection;
import com.dao.CloseTimeDao;
import com.dao.CoreWeightDao;
import com.dao.ProductDao;
import com.dao.PsProductDao;
import com.dao.ReceiptCompDao;
import com.dao.ReturnWeightLogDao;
import com.dao.ReturnWeightNumberDao;
import com.dao.StaffDao;
import com.dao.WeightElectricQueueDao;
import com.dao.WorkOrderDao;
import com.entity.CoreWeight;
import com.entity.DetailComp;
import com.entity.Product;
import com.entity.PsProduct;
import com.entity.ReceiptComp;
import com.entity.ResponseInfo;
import com.entity.ReturnWeightLog;
import com.entity.Staff;
import com.entity.TypeComp;
import com.entity.WeightElectricQueue;
import com.entity.WorkOrder;
import com.lctech.service.ImportDetailComp;
import com.lctech.service.PsWorkOrderService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;

@Controller

public class AjaxController {
	private static final Logger logger = Logger.getLogger(AjaxController.class);
	@Autowired
	private WorkOrderDao wodao;
	@Autowired
	private PsProductDao psdao;
	@Autowired
	private ProductDao pdao;
	@Autowired
	private ReceiptCompDao rcdao;
	@Autowired
	private PsWorkOrderService wosv;
	@Autowired
	private StaffDao staffdao;
	@Autowired
	private CloseTimeDao cldao;
	 @Autowired
		private DBPosgressConnection posgressConn;
		@Autowired
		private CoreWeightDao crdao;
		@Autowired
		private WeightElectricQueueDao weqdao;
		  @Autowired
		   private  Config config;
		  @Autowired
		   private  ReturnWeightLogDao rwldao;
		  @Autowired
		 private  ImportDetailComp imde;
		  @Autowired
			 private ReturnWeightNumberDao rwnd;
		  
	@RequestMapping(value = "/api/selectline", method = RequestMethod.GET)
	public @ResponseBody List<WorkOrder> selectline(@RequestParam Integer lineid){
		logger.info("selectline 12");
		List<WorkOrder> lwo = new ArrayList<WorkOrder>();
	    lwo = wodao.getListWorkOrderByLineStatus(lineid);
		return lwo;
	}
	@RequestMapping(value = "/api/selectbx", method = RequestMethod.GET)
	public @ResponseBody List<WorkOrder> selectlinebx(@RequestParam Integer lineid){
		logger.info("selectline 2");
		List<WorkOrder> lwo = new ArrayList<WorkOrder>();
	    lwo = wodao.getListWorkOrderByLineStatusIsBx(lineid);
		return lwo;
	}
	@RequestMapping(value = "/api/selectsetup", method = RequestMethod.GET)
	public @ResponseBody List<WorkOrder> selectlineSetup(@RequestParam Integer lineid){
		logger.info("selectline1");
		List<WorkOrder> lwo = new ArrayList<WorkOrder>();
	    lwo = wodao.getListWorkOrderByLineStatusIsSetup(lineid);
		return lwo;
	}
	@RequestMapping(value = "/api/selectrcid", method = RequestMethod.GET)
	public @ResponseBody ReceiptComp model(@RequestParam(value = "rcid", required = false) String rcid
			,@RequestParam(value = "woid", required = false) String woid){
		logger.info("selectmodel" +woid );
		ReceiptComp rc = new ReceiptComp();
		try {
		
		if(rcid==null || rcid==""){
			rc = wosv.getReceiptUpdatebyWo(woid);
		}else{
		 rc = wosv.getReceiptUpdate(rcid);
		}
		logger.info("rc" +rc.getWo().getId());
		}catch(Exception e) {
			logger.info(e.toString());
		}

		return rc;
	}
	@RequestMapping(value = "/api/selectrcid2", method = RequestMethod.GET)
	public @ResponseBody ReceiptComp model2(@RequestParam(value = "rcid", required = false) String rcid
			,@RequestParam(value = "woid", required = false) String woid){
		logger.info("selectmodel" +woid );
		ReceiptComp rc = new ReceiptComp();
	
		rc = wosv.collectShareCoreWeight(woid);
		return rc;
	}
	@RequestMapping(value = "/api/selectwoid", method = RequestMethod.GET)
	public @ResponseBody List<ReceiptComp> selectwoid(@RequestParam String woid){
		logger.info("selectwoid" +woid );
		
		List<ReceiptComp> lrc = rcdao.getbyIsPendingWaitWOid(woid);
		//logger.info("lrc" +lrc.get(0).getWo().getId() );
		return lrc;
	}

	@RequestMapping(value = "/api/selectclosetimeid", method = RequestMethod.GET)
	public @ResponseBody ReceiptComp selectlineid(@RequestParam Integer closetimeid,@RequestParam String woid){
		logger.info("selectlineid");
		ReceiptComp rc = new ReceiptComp();
		try {
		ReceiptComp lrc = rcdao.getbyClosetimeWoWait(woid,closetimeid,true);
		if(lrc!=null && lrc.getId()!=null){
			rc = wosv.getReceiptUpdate(lrc.getId());
		}else{
			rc = wosv.getReceiptUpdatebyWo(woid);
			if(closetimeid==null){
				rc.setClosetime(cldao.getNextTime(new Time(Calendar.getInstance().getTime().getTime())));
			}else{
			rc.setClosetime(cldao.getById(closetimeid));
		     }
		}
		}catch(Exception e) {
			logger.info(e.toString());
			return rc;
		}
		return rc;
	}
	@RequestMapping(value = "/api/delrcid", method = RequestMethod.GET)
	public @ResponseBody Boolean delrcid(@RequestParam String rcid){
		logger.info("delete rc delrcid rcid= " + rcid);
		try{
			rcdao.delete(rcdao.getbyId(rcid));
		}catch(Exception e){
			logger.info(e.toString());
			return false;
		}
		
		return true;
	}
	@RequestMapping(value = {"/api/getStaffByName"}, method = RequestMethod.POST)
	public void getStaffByName(HttpServletResponse response, @RequestParam(value="pname", required=true) String name) throws IOException{
		JSONObject json = new JSONObject();
		Staff p = staffdao.getByCode(name);
		json.put("result", "ok");
		json.put("id", p.getId());
		json.put("firstName", p.getFirstName());
		json.put("lastName", p.getLastName());
		json.put("part", p.getPart());
		json.put("position", p.getPosition());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());
	}
	@RequestMapping(value = {"/api/readexcel"}, method = RequestMethod.POST)
	public void  readExcel(HttpServletResponse response, @RequestParam("excelfile") MultipartFile excelfile) throws IOException{
		JSONObject json = new JSONObject();
		HashMap<String,List<ResponseInfo>>  listmap = imde.readExcelReponse(excelfile);
		json.put("result", "ok");
		json.put("listmap", listmap);
	
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());
	}
	@RequestMapping(value = {"/api/getSerial"}, method = RequestMethod.GET)
	public void getapiSerial(HttpServletResponse response, @RequestParam(value="serial", required=true) String serial) throws IOException{
		JSONObject json = new JSONObject();
	//	logger.info("api getserial" + serial);
		HashMap<String,String> item = posgressConn.getItemCode(serial);
		if(item != null && item.size()>1) {
	     String itemcode  = item.get("masp");
		Product p = pdao.getProductById(itemcode);
		//logger.info("api product" + p.toString());
		
		 List<Object[]> listcoreweight = crdao.getTypeCore();
		
		CoreWeight cw = crdao.getByItemcode(itemcode);
		logger.info(item.get("lotno"));

		json.put("result", "1");
		json.put("pt_part", p.getPt_part());
		json.put("pt_desc1", p.getPt_desc1());
		json.put("pt_desc2", p.getPt_desc2());
		json.put("lotno", item.get("lotno"));
		json.put("pt_um", p.getPt_um());
		json.put("vendor",cw.getVendor());
		json.put("coreweight", cw.getCoreweight());
		json.put("typecore",cw.getTypecore());
		json.put("rate",cw.getRate()==null || cw.getRate()==0 ? 1 :cw.getRate());
		json.put("listcoreweight",listcoreweight);
		}else {
			
			json.put("result", "0");
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());

	}
	@RequestMapping(value = {"/api/getptpart"}, method = RequestMethod.GET)
	public void getapiPtpart(HttpServletResponse response, @RequestParam(value="ptpart", required=true) String ptpart) throws IOException{
		JSONObject json = new JSONObject();
	//	logger.info("api getserial" + serial);
		//HashMap<String,String> item = posgressConn.getItemCode(ptpart);
		
		//if(item != null && item.size()>1) {
	    // String itemcode  = item.get("masp");
		Product p = pdao.getProductById(ptpart);
		//logger.info("api product" + p.toString());
		
		 List<Object[]> listcoreweight = crdao.getTypeCore();
		
		CoreWeight cw = crdao.getByItemcode(ptpart);
		//logger.info(item.get("lotno"));

		json.put("result", "1");
		json.put("pt_part", p.getPt_part());
		json.put("pt_desc1", p.getPt_desc1());
		json.put("pt_desc2", p.getPt_desc2());
		json.put("lotno", "");
		json.put("pt_um", p.getPt_um());
		json.put("vendor",cw.getVendor());
		json.put("coreweight", cw.getCoreweight());
		json.put("typecore",cw.getTypecore());
		json.put("rate",cw.getRate()==null || cw.getRate()==0 ? 1 :cw.getRate());
		json.put("listcoreweight",listcoreweight);
		
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());

	}
	@RequestMapping(value = {"/api/getSerialWeight"}, method = RequestMethod.GET)
	public void getapiSerialWeight(HttpServletResponse response) throws IOException{
		JSONObject json = new JSONObject();
		//logger.info("api getSerialWeight" );
		
	try {
		List<WeightElectricQueue> list  = weqdao.getAll();
		if(list != null && list.size()>0) {
			WeightElectricQueue w = list.get(list.size()-1);
			//if(w!=null) {
			logger.info(w.toString());
			json.put("result", "1");
			json.put("weight", w.getQty());
			//logger.info(w.toString());
			weqdao.deleteAll();
			/*}else {
				json.put("result", "0");
			}*/
		}else {
			json.put("result", "0");
		}
	}catch(Exception e) {
		logger.info(e);
		json.put("result", "0");
	}
	//fake

		/*json.put("result", "1");
		json.put("weight",20.0);*/
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());

	}
	@RequestMapping(value = {"/api/returnprint"}, method = RequestMethod.GET)
	public void getReturnPrinter(HttpServletResponse response,
			 @RequestParam(value="id", required=false) Long id
			 
			 ) throws IOException{
		logger.info("excutePrint");
	
		int error;
		String msg ="";
		  HashMap<String,Object> hmParams=new HashMap<String,Object>();
    	  
		
          
          
          //save log
          
          ReturnWeightLog rwl = new ReturnWeightLog();
          rwl = rwldao.getReturnWeightLog(id);
          
  
          
          hmParams.put("weight", rwl.getQty());
          hmParams.put("SerialNo", rwl.getSerialnew());
          hmParams.put("remark", rwl.getRemark());
          hmParams.put("partcode", rwl.getModel());
          hmParams.put("partnumber", rwl.getPtdesc1());
          hmParams.put("partname", rwl.getPtdesc2());
          hmParams.put("lotno", rwl.getLotno());
          hmParams.put("unit", rwl.getPtum());
          hmParams.put("vendor", rwl.getVendor());
          
	     String dirJasper1 =config.getReturnJasper();
	     String printerName = config.getPrinterName();
	     logger.info(dirJasper1);
	     logger.info(printerName);
	      File reportFile = new File(dirJasper1);
	      // If compiled file is not found, then compile XML template
	      if (!reportFile.exists()) {
	    	  logger.info(" Not found file jrxml ");
	    	  error = 3;
	              //   JasperCompileManager.compileReportToFile("D:/java/projectee/jasper1/src/main/webapp/WEB-INF/jasper/murata.jrxml","D:/java/projectee/jasper1/src/main/webapp/WEB-INF/jasper/murata.jasper");
	          }else{
	        	 
	        //  JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
	         try{
	        	  JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
	         
	        //  JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport,hmParams,conn);
	        	  JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, hmParams);
	          jasperprint.setOrientation(OrientationEnum.PORTRAIT);
	         // JasperExportManager.exportReportToPdfFile(jasperprint,"D:/java/projectee/jrp/WebContent/WEB-INF/jasper/murata1.pdf");
	          logger.info("giai doan print");
	      
	          
	         
	          
	          
	          
	          logger.info("printing ...");
	    
	          
	        
	          

	          JRPrintServiceExporter exporter;
	          
	          PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
	        //  printRequestAttributeSet.add(MediaSizeName.);
	          printRequestAttributeSet.add(new Copies(1));
	      // printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
	         // int width = Math.round(MediaSize.ISO..getX(MediaSize.MM));
	         // int height = Math.round(MediaSize.ISO.A4.getY(MediaSize.MM));
	          printRequestAttributeSet.add(new MediaPrintableArea(0, 0, 100, 50, MediaPrintableArea.MM));
	          //
	      
	       
	          PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
	          
	         /* Java AWT Printing will not find a printer via a path if it is not registered to 
	          the Windows / Active Directory user that is running the printing application. 
	          You must register the printer path through Windows "Devices and Printers" as 
	          a printer for that user for it to be visible. Then you must run lookupPrintServices 
	          to see the list of printers available and retrieve the proper PrintService by the exact name String listed*/
	          
	        
	          
	          javax.print.PrintService[] service = PrinterJob.lookupPrintServices(); 
	      

	          int count = service.length;
	          int i=0;
	          for ( i = 0; i < count; i++) {
	            
	              logger.info(service[i].getName());
	              if(service[i].getName().equals(printerName))
	              {
	            		  printServiceAttributeSet.add(new PrinterName(printerName, null));
	            		  break;
	              }
	          }
	          if(i==count){
	        	  logger.info(" Not found printer");
		    	  error =4;
		    	  msg = printerName;
	          }else{
	          
	        	 
	           exporter = new JRPrintServiceExporter();
	        
	         exporter.setExporterInput(new SimpleExporterInput(jasperprint));
	          SimplePrintServiceExporterConfiguration expConfig = new SimplePrintServiceExporterConfiguration();
	          expConfig.setPrintRequestAttributeSet(printRequestAttributeSet);
	          expConfig.setPrintServiceAttributeSet(printServiceAttributeSet);
	          expConfig.setDisplayPageDialog(Boolean.FALSE);
	          expConfig.setDisplayPrintDialog(Boolean.FALSE);
	        //  expConfig.setDisplayPrintDialog(true);
	          exporter.setConfiguration(expConfig);
	        // for(int c=1 ;i<= copies;i++) {
	        	  exporter.exportReport();
	         // }
	         
	         
	          logger.info(" success ");
	           error= 1;
	          
	          }
	         }catch(JRException e){
	        	 logger.info(" ko load file jrxml ");
		           error = 6;
		           msg =  reportFile.getPath();
		   
	         }
	          }
	      //namekh
		JSONObject json = new JSONObject();


		json.put("error", error);
		json.put("msg", msg);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());

	}
	@RequestMapping(value = {"/api/excutePrint"}, method = RequestMethod.GET)
	public void getPrinter(HttpServletResponse response,
			 @RequestParam(value="serialold", required=false) String serialold,
			 @RequestParam(value="vendor", required=false) String vendor,
			 @RequestParam(value="woid", required=true) String woid,
			 @RequestParam(value="woname", required=true) String woname,
			 @RequestParam(value="weight") Double weight,
			 @RequestParam(value="weightnet") Double weightnet,
			 @RequestParam(value="qty") Double qty,
			 @RequestParam(value="unit", required=true) String unit,
			 @RequestParam(value="remark") String remark,
			 @RequestParam(value="partcode", required=true) String partcode,
			 @RequestParam(value="partname", required=true) String partname,
			 @RequestParam(value="partnumber", required=true) String partnumber,
			 @RequestParam(value="lotno", required=false) String lotno,
			 @RequestParam(value="copies", required=false) Double copies) throws IOException{
		logger.info("excutePrint");
		if(copies == null ) {
			copies=1.0;
		}
		int error;
		String msg ="";
		  HashMap<String,Object> hmParams=new HashMap<String,Object>();
    	  
		  String serial = "";
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyy");
	        serial = simpleDateFormat.format(new Date());
	        
	        //serial += new Integer(100000 + new Random().nextInt(899999)).toString();*/
	
		  Integer serialnumber =  rwnd.getNumberbyDate(new Date());
		   String a = serialnumber.toString();
		   String b= "";
		   if(a.length()<6) {
			   for(int  i = a.length() ;i<6;i++) {
				   b+="0";
			   }
			   serial = serial + b + a;
		   }else {
			   serial = serial + a;
		   }
          hmParams.put("vendor", vendor);
          
          
          //save log
          
        
          
       
          
          hmParams.put("weight", qty);
          hmParams.put("SerialNo", serial);
          hmParams.put("remark", remark);
          hmParams.put("partname", partname);
          hmParams.put("partcode", partcode);
          hmParams.put("partnumber", partnumber);
          hmParams.put("lotno", lotno);
          hmParams.put("unit", unit);

          
	     String dirJasper1 =config.getReturnJasper();
	     String printerName = config.getPrinterName();
	     logger.info(dirJasper1);
	     logger.info(printerName);
	      File reportFile = new File(dirJasper1);
	      // If compiled file is not found, then compile XML template
	      if (!reportFile.exists()) {
	    	  logger.info(" Not found file jrxml ");
	    	  error = 3;
	              //   JasperCompileManager.compileReportToFile("D:/java/projectee/jasper1/src/main/webapp/WEB-INF/jasper/murata.jrxml","D:/java/projectee/jasper1/src/main/webapp/WEB-INF/jasper/murata.jasper");
	          }else{
	        	 
	        //  JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
	         try{
	        	  JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
	         
	        //  JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport,hmParams,conn);
	        	  JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, hmParams);
	          jasperprint.setOrientation(OrientationEnum.PORTRAIT);
	         // JasperExportManager.exportReportToPdfFile(jasperprint,"D:/java/projectee/jrp/WebContent/WEB-INF/jasper/murata1.pdf");
	          logger.info("giai doan print");
	      
	          
	         
	          
	          
	          
	          logger.info("printing ...");
	    
	          
	        
	          

	          JRPrintServiceExporter exporter;
	          
	          PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
	        //  printRequestAttributeSet.add(MediaSizeName.);
	          printRequestAttributeSet.add(new Copies(copies.intValue()));
	      // printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
	         // int width = Math.round(MediaSize.ISO..getX(MediaSize.MM));
	         // int height = Math.round(MediaSize.ISO.A4.getY(MediaSize.MM));
	          printRequestAttributeSet.add(new MediaPrintableArea(0, 0, 100, 50, MediaPrintableArea.MM));
	          //
	      
	       
	          PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
	          
	         /* Java AWT Printing will not find a printer via a path if it is not registered to 
	          the Windows / Active Directory user that is running the printing application. 
	          You must register the printer path through Windows "Devices and Printers" as 
	          a printer for that user for it to be visible. Then you must run lookupPrintServices 
	          to see the list of printers available and retrieve the proper PrintService by the exact name String listed*/
	          
	        
	          
	          javax.print.PrintService[] service = PrinterJob.lookupPrintServices(); 
	      

	          int count = service.length;
	          int i=0;
	          for ( i = 0; i < count; i++) {
	            
	              logger.info(service[i].getName());
	              if(service[i].getName().equals(printerName))
	              {
	            		  printServiceAttributeSet.add(new PrinterName(printerName, null));
	            		  break;
	              }
	          }
	          if(i==count){
	        	  logger.info(" Not found printer");
		    	  error =4;
		    	  msg = printerName;
	          }else{
	          
	        	 
	           exporter = new JRPrintServiceExporter();
	        
	         exporter.setExporterInput(new SimpleExporterInput(jasperprint));
	          SimplePrintServiceExporterConfiguration expConfig = new SimplePrintServiceExporterConfiguration();
	          expConfig.setPrintRequestAttributeSet(printRequestAttributeSet);
	          expConfig.setPrintServiceAttributeSet(printServiceAttributeSet);
	          expConfig.setDisplayPageDialog(Boolean.FALSE);
	          expConfig.setDisplayPrintDialog(Boolean.FALSE);
	        //  expConfig.setDisplayPrintDialog(true);
	          exporter.setConfiguration(expConfig);
	        // for(int c=1 ;i<= copies;i++) {
	        	  exporter.exportReport();
	         // }
	         
	         
	          logger.info(" success ");
	           error= 7;
	           ReturnWeightLog rwl = new ReturnWeightLog();
	           rwl.setCreatedate(new Timestamp(new Date().getTime()));
	           rwl.setWo(woid);
	           rwl.setModel(partcode);
	           rwl.setQty(weight);
	           rwl.setRemark(remark);
	           rwl.setLotno(lotno);
	           rwl.setQty(qty);
	           rwl.setSerialold(serialold);
	           rwl.setSerialnew(serial);
	           rwl.setWeight(weight);
	           rwl.setWeightnet(weightnet);
	           rwl.setPtdesc1(partnumber);
	           rwl.setPtum(unit);
	           rwl.setStatus(true);
	           rwl.setWoname(woname);
	           rwl.setPtdesc2(partname);
	           rwl.setVendor(vendor);
	           rwldao.save(rwl);
	           logger.info("save rwl "+rwl.toString() );
	          }
	         }catch(JRException e){
	        	 logger.info(" ko load file jrxml ");
		           error = 6;
		           msg =  reportFile.getPath();
		   
	         }
	          }
	      //namekh
		JSONObject json = new JSONObject();


		json.put("error", error);
		json.put("msg", msg);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());

	}
}
