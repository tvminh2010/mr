package com.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.config.Config;
import com.dao.ProductDao;
import com.entity.Product;
import com.entity.PsProduct;
import com.entity.WorkOrder;
import com.lctech.service.ImportProduct;



@Controller
@RequestMapping({"/model"})
public class ItemMasterController {
	private static final Logger logger = Logger.getLogger(ItemMasterController.class);

   @Autowired
   private ProductDao pdao;
	@Autowired
	   private Config config;
	@Autowired
	private ImportProduct importP;
	
	@RequestMapping(method = RequestMethod.GET )
	public String getmodel(Model model, @RequestParam(value = "page",required = false) Integer page,
			   @RequestParam(value = "error",required = false) Integer error,
			   @RequestParam(value = "msg",required = false) Integer msg){
		List<Product> lp = pdao.listProduct();
	
		 model.addAttribute("error", error);
		 model.addAttribute("msg", msg);
		 model.addAttribute("lp", lp);
		 return "model";
	}
	  @RequestMapping(value="importModel",method = RequestMethod.POST)
	   public String importPlan(
			   @RequestParam("excelfile") MultipartFile excelfile
			 )  {
		/*  try{*/
		  List<Product> lwo = importP.readExcel(excelfile);
		  pdao.saveAll(lwo);
	/*  }catch(Exception e){
		  e.printStackTrace();
		  return "redirect:/model?error=1";
	  }*/
	  	return "redirect:/model?msg=1";
	 }
	  @RequestMapping(value="importPackage",method = RequestMethod.POST)
	   public String importPackage(
			   @RequestParam("excelfile") MultipartFile excelfile
			 )  {
		  try{
		  List<Product> lwo = importP.readExcelPackage(excelfile);
		  pdao.saveAll(lwo);
		  }catch(Exception e){
				logger.debug(e);

			  e.printStackTrace();
			  return "redirect:/model?error=1";
		  }
	      
	  	return "redirect:/model?msg=1";
	 }
}
