package com.lctech.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DetailCompDao;
import com.dao.ReceiptCompDao;
import com.dao.TurnDao;
import com.dao.WorkOrderDao;
import com.entity.CloseTime;
import com.config.Config;
import com.entity.DetailComp;
import com.entity.Product;
import com.entity.ReceiptComp;
import com.entity.Turn;
import com.entity.TypeComp;
import com.entity.WorkOrder;

@Service
public class ReportsServive {
	private static final Logger logger = Logger.getLogger(ReportsServive.class);
	@Autowired
	private DetailCompDao dcdao;
	@Autowired
	private WorkOrderDao wodao;
	@Autowired
	private ReceiptCompDao rcdao;
	@Autowired
	private TurnDao tdao;
	@Autowired
	private Config config;

	public 	List<List<Object>> getAddListReponse(String woname){
		List<Object[]> l = new ArrayList<Object[]>();
		
		List<List<Object>> l2 = new ArrayList<List<Object>>();
		
		l = dcdao.getDetailCompByWoidSetupBS(woname);
		for(Object[] ol : l){
			List<Object[]> l1 = new ArrayList<Object[]>();
			List<Object> l3 = new ArrayList<Object>();
			DetailComp dc = (DetailComp)ol[0];
			ReceiptComp rc = (ReceiptComp)ol[1];
			l3.add(dc);
			l3.add(rc);
			 l1 = dcdao.getDetailCompByWoidReponse(rc.getTurn().getId(), dc.getModel().getPt_part(),rc.getWo().getId());
			 if(l1!=null && l1.size()>0){
				DetailComp dc1 = (DetailComp)l1.get(0)[0];
				ReceiptComp rc1 = (ReceiptComp)l1.get(0)[1];
				l3.add(dc1);
				l3.add(rc1);
			 }
			 l2.add(l3);
		}
		return l2;
		
	}
}
