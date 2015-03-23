package com.thoughtservice.portal.login.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Custom portal user object which will be available in session.
 * 
 * @author Shinu
 *
 */
public class PortalUserDetails extends User {

	private final String userId;
	private final String department;
	private final String firstName;
	private final String emailId;
	private boolean isAdmin;
	

	public PortalUserDetails(String id, String department, String username, String password, 
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			List<GrantedAuthority> noAuthorities, String firstName, String emailId) {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, noAuthorities);
		this.userId = id;
		this.department=department;
		this.firstName = firstName;
		this.emailId = emailId;
		
		
		for(GrantedAuthority auth : noAuthorities) {
			if("ROLE_ADMIN".equals(auth.getAuthority())) {
				this.isAdmin = true;
				break;
			}
		}
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6452482939011117510L;

	public String getUserId() {
		return userId;
	}

	public String getDepartment() {
		return department;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmailId() {
		return emailId;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

}
