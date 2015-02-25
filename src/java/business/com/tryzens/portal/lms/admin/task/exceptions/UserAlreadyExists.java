package com.tryzens.portal.lms.admin.task.exceptions;

public class UserAlreadyExists extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6998948293490147048L;

	public UserAlreadyExists() {
		super();
	}
	
	public UserAlreadyExists(String message){
		super(message);
	}
	
}
