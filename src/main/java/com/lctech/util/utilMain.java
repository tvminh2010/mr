package com.lctech.util;

import java.util.ArrayList;
import java.util.List;

public class utilMain {
	
	static public List<String> getLidString(String lid){
		List<String> ids = new ArrayList<String>();
		//String lid = processlistinspection.getLsta().g().getId();
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
	static public List<Integer> getLidInteger(String lid){
		List<Integer> lids = new ArrayList<Integer>();
	
		List<String> lsid = getLidString(lid);
		for(String ids :lsid){
			int id = Integer.parseInt(ids);
			lids.add(id);
		}
				
	
		  return lids;
	}
}
