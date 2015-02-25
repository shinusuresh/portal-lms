package com.tryzens.portal.lms.admin.task.exceptions;

public class SkillAlreadyExistsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5986233774196957061L;

	public SkillAlreadyExistsException() {
		super();
	}
	
	public SkillAlreadyExistsException(String message){
		super(message);
	}
}
