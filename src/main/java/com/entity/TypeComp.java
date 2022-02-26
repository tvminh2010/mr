package com.entity;

public enum TypeComp {
	RequestSetup(0),
	RequestBS(1),
	Response(2),
	Return(3),
	Delete(4);
	
	
	
	private final Integer type;
	TypeComp(Integer type) {
	    this.type = type;
	}
	public Integer getType() {
		return type;
	}

	}
