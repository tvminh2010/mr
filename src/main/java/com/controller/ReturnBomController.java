package com.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dao.CoreWeightDao;
import com.dao.ProductDao;
import com.entity.CoreWeight;
import com.entity.CoreWeightInfo;
import com.entity.Product;
import com.entity.PsProduct;
import com.lctech.service.ImportPsProduct;

@Controller
@RequestMapping({"/returnbom"})
public class ReturnBomController {
	private static final Logger logger = Logger.getLogger(ReturnBomController.class);

	@Autowired
	private CoreWeightDao cwdao;
	@Autowired
	private ProductDao pdao;
	 @Autowired
		private  ImportPsProduct importB;
	@RequestMapping(method = RequestMethod.GET )
	public String getbom(Model model,
			   @RequestParam(value = "error",required = false) Integer error,
			   @RequestParam(value = "msg",required = false) Integer msg,
			   @RequestParam(value = "mg",required = false) String mg){

		List<CoreWeightInfo> lcwi = new ArrayList<CoreWeightInfo>();
		try {
		List<CoreWeight> lps = cwdao.getListAll();
		for(CoreWeight cw : lps) {
			CoreWeightInfo cwi = new CoreWeightInfo();
			Product p = pdao.getProductById(cw.getPt_part());
			cwi.setCw(cw);
			cwi.setP(p);
			lcwi.add(cwi);
		}
		 model.addAttribute("lcwi", lcwi);
		 model.addAttribute("error", error);
		 model.addAttribute("msg", msg);
		 model.addAttribute("mg", mg);
		 model.addAttribute("lps", lps);
		}catch(Exception e) {
			logger.debug(e);
			e.printStackTrace();
		}
		 return "returnbom";
	}
	  @RequestMapping(value="/importReturnBom",method = RequestMethod.POST)
	   public String importPlan(Model model,
			   @RequestParam("excelfile") MultipartFile excelfile
			 )  {
		 
		 try{
		  List<CoreWeight> lwo = importB.readExcelReturnBom(excelfile);
		  List<CoreWeight> lold = cwdao.getListAll();
		  cwdao.deleteAll();
			for(CoreWeight p:lwo){
				
			  //  Product par = pdao.getProductById(p.getPt_part());
			  //  if(par!=null && par.getPt_part()!=null){
			    
			   
				
			    	cwdao.save(p);
			   // }
			}
		
	  }catch(Exception e){
		  logger.debug(e);
			  e.printStackTrace();
		
			  return "redirect:/returnbom?error=1&mg="+e.toString();
			  
		  }
	      
	  	return "redirect:/returnbom?msg=1";
	 }
	
}
