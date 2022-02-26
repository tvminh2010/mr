package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.config.DBSqlServerConnection;
import com.dao.RoleDao;
import com.dao.StaffDao;
import com.dao.UserDao;

import com.entity.Role;
import com.entity.Staff;

import com.entity.User;

@Controller
@RequestMapping({"/admin/"})
public class AdminController {
	private static final Logger logger = Logger.getLogger(AdminController.class);
	@Autowired
	private UserDao usdao;
	@Autowired
	private StaffDao staffdao;
	@Autowired
	private RoleDao roledao;

	@RequestMapping(value = {"/controlPanel"}, method = RequestMethod.GET)
	public String getAdminControlPanel(Model model){
		User us = usdao.getUserByName(getPrincipal());
		 Staff sff = staffdao.getByID(us.getStaff().getId());
		    us.setStaff(sff);
		    Role r = roledao.getById(us.getRole().getId());
		    us.setRole(r);
		model.addAttribute("user", us);
		List<String> role=usdao.getRole(getPrincipal());
		model.addAttribute("role", role);
		List<Staff> list = staffdao.getList();
		model.addAttribute("listStaff", list);
		return "controlPanel";
	}

	@RequestMapping(value = {"/controlPanel"}, method = RequestMethod.POST)
	public String postAdminControlPanel(Model model, @ModelAttribute("user")User profile){
		User us = usdao.getUserByName(getPrincipal());
		if(us.getId()!=profile.getId()) model.addAttribute("user", us);
		else{
			us.setPass(profile.getPass());
			 Staff sff = staffdao.getByID(us.getStaff().getId());
			    us.setStaff(sff);
			    Role r = roledao.getById(us.getRole().getId());
			    us.setRole(r);
			usdao.update(us);
			model.addAttribute("user", us);
		}
		List<String> role=usdao.getRole(getPrincipal());
		model.addAttribute("role", role);
		List<Staff> list = staffdao.getList();
		model.addAttribute("listStaff", list);
		return "controlPanel";
	}

	@RequestMapping(value = {"/staff"}, method = RequestMethod.GET)
	public String getAdminStaffManager(Model model, @RequestParam(value="key", required=false) String code,
			@RequestParam(value="del", required=false)String del){
	
		if(code==null||code=="") model.addAttribute("staff", new Staff());
		else model.addAttribute("staff", staffdao.getByCode(code));
		if(del!=null &&  del !=""){
			
			try {
				staffdao.delete(del);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e);

				e.printStackTrace();
				model.addAttribute("errordel", 1);
			}
		}
		List<Staff> list = staffdao.getList();
		model.addAttribute("listStaff", list);
		return "staff";
	}	

	@RequestMapping(value = {"/staff"}, method = RequestMethod.POST)
	public String postAdminStaffManager(Model model, @ModelAttribute("staff")Staff staff){
		
	    if(staff.getId()!=null && staff.getId()!=""){
	    	staffdao.update(staff);
	    }else{
	    	Staff st = staffdao.getByCode(staff.getCode());
	    	if(st == null){
	    	staff.setId(staff.getCode());
	    	staffdao.insert(staff);
	    	}else{
	    		model.addAttribute("error", 1);
	    	}
	    }
		
		model.addAttribute("staff", new Staff());
		List<Staff> list = staffdao.getList();
		model.addAttribute("listStaff", list);
		return "staff";
	}	
	
	@RequestMapping(value = {"/user"}, method = RequestMethod.GET)
	public String getAdminUserManager(Model model, @RequestParam(value="key", required=false) Integer uid,
			 @RequestParam(value="del", required=false) Integer del){
		User  user  = new User();
		if(uid!=null){
			 user = usdao.getUserById(uid);
			Staff sta = staffdao.getByID(user.getStaff().getId());
			user.setStaff(sta);
			model.addAttribute("user", user);
		}else if(del!=null){
			try {
				usdao.delete(del);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e);

				e.printStackTrace();
				model.addAttribute("errordel", 1);
			}
			 user = new User();
			model.addAttribute("user", user);
		}else{
			 user  = new User();
			
		}
		model.addAttribute("us",user);
		List<User> us = usdao.getList();
		model.addAttribute("listUser", us);
		List<Staff> list = staffdao.getList();
		model.addAttribute("listStaff", list);
		List<Role> lpr = roledao.getList();
		model.addAttribute("lpr", lpr);
		

		
		return "user";
	}
	
	private List<String> getLid(String lid){
		List<String> ids = new ArrayList<String>();
	
		if(lid!=null && lid!=""){
			
			int j = lid.length();
		    for(int i=0;i<j;i++){
		    	
		    	String id= "";
				
				if(lid.indexOf(",")==-1){
					id = lid;
					i=j;
				}else{
				id = lid.substring(0, lid.indexOf(","));
				lid=lid.substring(lid.indexOf(",")+1, lid.length());
				i = i + id.length();
				}
				
					
					ids.add(id);
			    }
				
				
		    
		  
		}
		  return ids;
	}
	@RequestMapping(value = {"/user"}, method = RequestMethod.POST)
	public String postAdminUserManager(Model model, @ModelAttribute("us")User user,
			@RequestParam(value="lid", required=false) String lid){
		
		
		if(lid!=null){
		List<String> lpi = getLid(lid);
	
	
		}
		if(user.getId()==null  ){
		Staff st = staffdao.getByCode(user.getStaff().getCode());
		if(st==null){
			model.addAttribute("error", 1);
		}else{
		user.setStaff(st);
		Role r=roledao.getByRole(user.getRole().getId());
	     user.setRole(r);
	 
		usdao.insert(user);
		}
		}else{
			Staff st = staffdao.getByCode(user.getStaff().getCode());
			user.setStaff(st);
			Role r=roledao.getByRole(user.getRole().getId());
		     user.setRole(r);
		      User use = usdao.getUserById(user.getId());
		      if(use !=null){
		    	
		      }
		
			usdao.update(user);
		}
		model.addAttribute("user",new User());
		List<User> us = usdao.getList();
		model.addAttribute("listUser", us);
		List<Staff> list = staffdao.getList();
		model.addAttribute("listStaff", list);
		List<Role> lpr = roledao.getList();
		model.addAttribute("lpr", lpr);
	
		return "user";
	}
	
	
	@RequestMapping(value = {"/profile"}, method = RequestMethod.POST)
	public String postAdminControlPanel(Model model,@ModelAttribute("pass")String pass){
		User us = usdao.getUserByName(getPrincipal());
		us.setPass(pass);
		 Staff sff = staffdao.getByID(us.getStaff().getId());
		    us.setStaff(sff);
		    Role r = roledao.getById(us.getRole().getId());
		    us.setRole(r);
		usdao.update(us);
		model.addAttribute("user", us);
	
		return "controlPanel";
	}
	@RequestMapping(value = {"/changepass"}, method = RequestMethod.POST)
	public String changepass(Model model,@ModelAttribute("pass")String pass
		){
		
		try{
		User us = usdao.getUserByName(getPrincipal());
		us.setPass(pass);
		usdao.update(us);
		}catch(Exception e){
			logger.debug(e);

			e.printStackTrace();
			return "changepass?error=1";
		}
	
		return "redirect:changepass?msg=1";
	}
	@RequestMapping(value = {"/changepass"}, method = RequestMethod.GET)
	public String changepass(Model model ,@RequestParam(value = "error",required = false) Integer error,
			   @RequestParam(value = "msg",required = false) Integer msg){
		  model.addAttribute("error", error);
	        model.addAttribute("msg", msg);
		return "changepass";
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
	
}
