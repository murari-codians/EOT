package com.eot.util;

public class EotException  extends Exception {
 
	String message;
	
	public EotException(String message) {
		super();
		this.message=message;
	}
	public EotException()
	{
		super();	
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
