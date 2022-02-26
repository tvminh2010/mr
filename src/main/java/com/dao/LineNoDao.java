package com.dao;

import java.util.List;



import com.entity.LineNo;

public interface LineNoDao {

public List<LineNo> getlistLineNo();

  
	public Integer getLineNoId();
	public LineNo getLineNoName(String name);
	public void createLineNo(String name);
	
	public void insert(LineNo ln);
	
	
	



	LineNo lineBYID(int id);

	boolean checkExit(String lineno, Integer part);

	void update(LineNo ln);



	void delete(int id);
}
