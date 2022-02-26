package com.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dao.CloseTimeDao;
import com.dao.DetailCompDao;
import com.dao.LineNoDao;
import com.dao.ProductDao;
import com.dao.ReceiptCompDao;
import com.dao.TurnDao;
import com.dao.UserDao;
import com.dao.WorkOrderDao;
import com.entity.CloseTime;
import com.config.Config;
import com.entity.DetailComp;
import com.entity.ReceiptComp;
import com.entity.TypeComp;
import com.entity.User;
import com.entity.WorkOrder;
import com.lctech.service.ExportExcel;
import com.lctech.service.ImportDetailComp;
import com.lctech.service.PsWorkOrderService;
import com.lctech.service.ImportWorkOrder;

@Controller
@RequestMapping({ "/yeucauNVL/", "/yeucauNVL/input" })
// @Scope("session")
public class LineController {

	private static final Logger logger = Logger.getLogger(LineController.class);
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
	private TurnDao tdao;;
	@Autowired
	private ImportDetailComp imDetailComp;
	@Autowired
	private UserDao usdao;
	@Autowired
	private Config config;
	@Autowired
	private CloseTimeDao ctdao;
	@Autowired
	private PsWorkOrderService pswoservice;

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model, @RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "woid", required = false) String woid,
			@RequestParam(value = "error", required = false) Integer error,
			@RequestParam(value = "msg", required = false) Integer msg) {
		logger.info("yeucauNVL");

		ReceiptComp rc = new ReceiptComp();
		List<ReceiptComp> lrc = new ArrayList<ReceiptComp>();
		if (woid != null) {
			rc = pswoservice.getReceiptUpdatebyWo(woid);
			lrc = rcdao.getbyIsPendingWaitWOid(woid);

		}
		if (id != null) {
			rc = pswoservice.getReceiptUpdate(id);
			lrc = rcdao.getbyIsPendingWaitWOid(rc.getWo().getId());

		}
		model.addAttribute("rc", rc);
		model.addAttribute("lrc", lrc);
		model.addAttribute("error", error);
		model.addAttribute("msg", msg);
		model.addAttribute("listline", lndao.getlistLineNo());
		model.addAttribute("lclosetime", ctdao.getList());
		model.addAttribute("typesetup", TypeComp.RequestSetup);
		model.addAttribute("typebs", TypeComp.RequestBS);
		model.addAttribute("typesetreturn", TypeComp.Return);

		CloseTime lnt = ctdao.getNextTime();
		model.addAttribute("timeclose", lnt);
		return "yeucauNVL";
	}

	@RequestMapping(value = "yeucauNVL", method = RequestMethod.POST)
	public String yeucauNVLPost(Model model, @ModelAttribute("rc") ReceiptComp rc) {

		int i = 0; 
		
		//logger.info("yeucauNVLPost   type: " + rc.getType().getType());
		//logger.info("yeucauNVLPost turn:" + rc.getTurn().getD() );

		WorkOrder wo = wodao.getWObyId(rc.getWo().getId());
		for (DetailComp dc : rc.getlDetailComp()) {
			if (dc.getQty() != null && dc.getQty() > 0) {
				i++;
			}
		}
		logger.info("i="+ i);
		if (i > 0) {
		
			User us = usdao.getUserByName(getPrincipal());
			if (wo.getStatus() == 1) {
				rc.setType(TypeComp.RequestSetup);
			} else {
				rc.setType(TypeComp.RequestBS);
			}

			rc.setWo(wo);
			rc.setUs(us);

			if (wo.getStatus() == 1) {
				wo.setStatus(2);
			}
			rc.setDate(new Timestamp(new Date().getTime()));

			rc.setIsWait(true);
			Boolean update = false;
			if (rc.getId() == null || rc.getId().equalsIgnoreCase("") || rc.getId() == "null") {
				// update
				ReceiptComp lrc = rcdao.getbyClosetimeWoWait(rc.getWo().getId(), rc.getClosetime().getId(), true);
				if (lrc != null && lrc.getId() != null) {
					return "redirect:/yeucauNVL/input?error=7";
				}
				String id = UUID.randomUUID().toString();
				rc.setId(id);
			} else {
				update = true;
			}
			// wodao.save(wo);
			rcdao.save(rc);
			for (DetailComp dc : rc.getlDetailComp()) {
				// if(dc.getQty() != null && dc.getQty()>0){
				if (dc.getId() == null || dc.getId().equalsIgnoreCase("") || dc.getId() == "null") {
					String dcid = UUID.randomUUID().toString();
					dc.setId(dcid);
				}
				dc.setReceipt(new ReceiptComp(rc.getId()));
				dc.setModel(pdao.getProductById(dc.getModel().getPt_part()));
				dc.setQty(dc.getQty() == null ? 0 : dc.getQty());
				dcdao.save(dc);

				// }

			}

			// return "yeucauNVL"; ok

			if (!update) {
				return "redirect:/yeucauNVL/input?id=" + rc.getId() + "&msg=1";
			} else {
				return "redirect:/yeucauNVL/input?id=" + rc.getId() + "&msg=2";
			}
		} else {
			logger.info("ko co NVL naof dc nhap");
			if (rc.getId() != null && !rc.getId().equalsIgnoreCase("") && rc.getId() != "null") {

				ReceiptComp rc1 = rcdao.getbyId(rc.getId());
				logger.info("delrcid rcid= " + rc.getId());
				rcdao.delete(rc1);
			}
			return "redirect:/yeucauNVL/input?woid=" + wo.getId() + "&msg=3";
		}

	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {

			userName = ((UserDetails) principal).getUsername();
		} else {

			userName = principal.toString();
		}
		return userName;
	}
}
