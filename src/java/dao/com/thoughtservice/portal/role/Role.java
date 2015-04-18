package com.thoughtservice.portal.role;

import java.util.HashSet;
import java.util.Set;

import com.thoughtservice.portal.PersistentObject;
import com.thoughtservice.portal.user.User;

public class Role extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 494767008796741703L;
	private String name;
	private String description;
	private Set<User> users = new HashSet<User>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
