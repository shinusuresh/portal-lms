package com.thoughtservice.portal.lms.mail;

import java.util.List;

public abstract class UserNotification extends EmailBase {
	
	@Override
	public String template() {
		return "notification.html";
		
	}
	
	public List<String> recepients()
	{
		return null;
	}
	
}
