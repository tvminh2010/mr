package com.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.config.Config;
import com.dao.ProductDao;
import com.dao.PsProductDao;
import com.entity.Product;
import com.entity.PsProduct;
import com.lctech.service.ImportPsProduct;
@Controller
@RequestMapping({"/bom"})
public class BomController {
	private static final Logger logger = Logger.getLogger(BomController.class);
	@Autowired
	   private Config config;
	 @Autowired
	    private PsProductDao psdao;
	 @Autowired
	private  ImportPsProduct importB;
	 @Autowired
	    private ProductDao pdao;
		@RequestMapping(method = RequestMethod.GET )
		public String getbom(Model model,
				   @RequestParam(value = "error",required = false) Integer error,
				   @RequestParam(value = "msg",required = false) Integer msg,
				   @RequestParam(value = "mg",required = false) String mg){
	
			List<PsProduct> lps = psdao.getLPs();
			 model.addAttribute("error", error);
			 model.addAttribute("msg", msg);
			 model.addAttribute("mg", mg);
			 model.addAttribute("lps", lps);
			 return "bom";
		}
		  @RequestMapping(value="/importBom",method = RequestMethod.POST)
		   public String importPlan(Model model,
				   @RequestParam("excelfile") MultipartFile excelfile
				 )  {
			 
			 try{
				  Set<String> lpsnot = new HashSet<String>();
			  List<PsProduct> lwo = importB.readExcel(excelfile);
			
		/*		for(PsProduct p:lwo){
					
				    Product par = pdao.getProductById(p.getPs_par().getPt_part());
				    if(par!=null && par.getPt_part()!=null){
				    	 Product comp = pdao.getProductById(p.getPs_comp().getPt_part());
				    	  if(comp==null){
				    		  lpsnot.add(p.getPs_comp().getPt_part());
				    		 // return "redirect:/bom?error=10&mg="+p.getPs_comp().getPt_part();
				    	  }
				    	  }else{
				    		  lpsnot.add(p.getPs_par().getPt_part());
							 // return "redirect:/bom?error=10&mg="+p.getPs_par().getPt_part();

				    }
					
					
				}*/
			/*	if(lpsnot!=null && lpsnot.size()>0){
					return "redirect:/bom?error=10&mg="+lpsnot.toString();
				}*/
				 //psdao.deleteAll();
			  psdao.saveAll(lwo);
				/*	for(PsProduct p:lwo){
					    psdao.save(p);
					}*/
			
		  }catch(Exception e){
				logger.info(e);
                  System.out.println(e);
				 // e.printStackTrace();
			
				  return "redirect:/bom?error=1&mg="+e.toString();
				  
			  }
		      
		  	return "redirect:/bom?msg=1";
		 }
	
}
