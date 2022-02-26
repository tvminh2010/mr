package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.CloseTimeDao;
import com.entity.CloseTime;
import com.lctech.util.utilMain;

@Controller
@RequestMapping({"/closetime"})
public class CloseTimeController {
	private static final Logger logger = Logger.getLogger(CloseTimeController.class);
	 @Autowired
	 
	 private  CloseTimeDao ctdao;
	
	   @RequestMapping(method = RequestMethod.GET)
	   public String closetime(ModelMap model,
			   @RequestParam(value = "error",required = false) Integer error,
			   @RequestParam(value = "msg",required = false) Integer msg,
			   @RequestParam(value = "id",required = false) Integer id)  {
		   logger.info("closetime");
		   CloseTime closetime = new CloseTime();
		   
		   List<CloseTime> cl = ctdao.getList();
		   model.addAttribute("cl", cl);
		   model.addAttribute("error", error);
		   model.addAttribute("msg", msg);
		   if(id!=null && id >=0){
			   closetime = ctdao.getById(id);
		   }
		   model.addAttribute("closetime", closetime);
	      return "closetime";
	 }
	   @RequestMapping(method = RequestMethod.POST)
	   public String closetimePost(Model model,@ModelAttribute("closetime")CloseTime closetime)  {
		   logger.info("closetime post");
		  Boolean check = ctdao.checkDouble(closetime);
		  try{
		  if(check){
			  return "redirect:/closetime?error=13";
		  }
		   ctdao.save(closetime);
		  }catch(Exception e){ 
			  return "redirect:/closetime?error=1";
		  }
	      return "redirect:/closetime?msg=1";
	 }
	   @RequestMapping(value="deletect",method = RequestMethod.GET)
	   public String deletect(Model model, @RequestParam(value = "id",required = false)Integer id)  {
		   logger.info("delete closetime");
		   try{
		   CloseTime closetime = ctdao.getById(id);
		   ctdao.delete(closetime);
		   }catch(Exception e){ 
				  return "redirect:/closetime?error=1";
			  }
	      return "redirect:/closetime?msg=1";
	 }
	   @RequestMapping(value="deletectall",method = RequestMethod.GET)
	   public String deletect(Model model, @RequestParam(value = "lid",required = false)String lid)  {
		   logger.info(lid);
		   try{
		   ctdao.deletelist(lid);
	   }catch(Exception e){ 
			  return "redirect:/closetime?error=1";
		  }
	      return "redirect:/closetime?msg=1";
	 }
}
