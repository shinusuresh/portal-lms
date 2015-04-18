package com.thoughtservice.portal.user.dao;

import java.util.List;

import com.thoughtservice.portal.role.Role;

public interface RoleDao {

	List<Role> findAllRoles();
	
	Role findRoleByName(String name);
	
	Role findRoleById(long id);

	Role findUserRoleByUserId(Long userId);
	
}
