package com.lctech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CloseTimeDao;
import com.entity.CloseTime;
@Service
public class CloseTimeService {
	@Autowired
	private CloseTimeDao cldao;
	public List<String> getCron(){
		List<String> lcron = new ArrayList<String>();
		 List<CloseTime> lct =  cldao.getList();
		 for(CloseTime ct:lct){
			 int hh = ct.getClosetime().getHours();
			 int mm = ct.getClosetime().getMinutes();
			 String cron = "0 "+ mm +" "+ hh +" * * *";
			 lcron.add(cron);
		 }
		 return lcron;
	}
}
