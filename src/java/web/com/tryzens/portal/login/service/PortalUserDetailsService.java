package com.tryzens.portal.login.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

public class PortalUserDetailsService extends JdbcDaoImpl {

	public static final String CUSTOM_USERS_BY_USERNAME_QUERY = "select user_id, department, email, password, status, first_name, email from user where email=?";
	public static final String CUSTOM_AUTHORITIES_BY_USERNAME_QUERY = "select u.email,r.name, ur.role_id from user u, role r, user_roles ur where u.user_id = ur.user_id and u.email=? and r.role_id=ur.role_id;";

	@SuppressWarnings("deprecation")
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		List<UserDetails> users = loadUsersByUsername(username);

		if (users.size() == 0) {
			logger.debug("Query returned no results for user '" + username
					+ "'");

			throw new UsernameNotFoundException(messages.getMessage(
					"JdbcDaoImpl.notFound", new Object[] { username },
					"Username {0} not found"), username);
		}

		PortalUserDetails user = (PortalUserDetails) users.get(0); // contains no GrantedAuthority[]

		Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();

		if (getEnableAuthorities()) {
			dbAuthsSet.addAll(loadUserAuthorities(user.getUsername()));
		}

		if (getEnableGroups()) {
			dbAuthsSet.addAll(loadGroupAuthorities(user.getUsername()));
		}

		List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(
				dbAuthsSet);

		addCustomAuthorities(user.getUsername(), dbAuths);

		if (dbAuths.size() == 0) {
			logger.debug("User '" + username
					+ "' has no authorities and will be treated as 'not found'");

			throw new UsernameNotFoundException(messages.getMessage(
					"JdbcDaoImpl.noAuthority", new Object[] { username },
					"User {0} has no GrantedAuthority"), username);
		}

		return createUserDetails(user.getUserId(), user.getDepartment(), username, user, dbAuths, user.getFirstName(), user.getEmailId());
	}

	protected UserDetails createUserDetails(String id, String department, String username,
			UserDetails userFromUserQuery,
			List<GrantedAuthority> combinedAuthorities, String firstName, String emailId) {
		String returnUsername = userFromUserQuery.getUsername();

		if (!isUsernameBasedPrimaryKey()) {
			returnUsername = username;
		}

		return new PortalUserDetails(id, department,  returnUsername,
				userFromUserQuery.getPassword(), userFromUserQuery.isEnabled(),
				true, true, true, combinedAuthorities, firstName, emailId);
	}

	@Override
	protected List<UserDetails> loadUsersByUsername(String username) {
		return getJdbcTemplate().query(CUSTOM_USERS_BY_USERNAME_QUERY,
				new String[] { username }, new RowMapper<UserDetails>() {
					public UserDetails mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String id = rs.getString(1);
						String department= rs.getString(2);
						String username = rs.getString(3);
						String password = rs.getString(4);
						boolean enabled = rs.getBoolean(5);
						String firstName = rs.getString(6);
						String email = rs.getString(7);
						return new PortalUserDetails(id, department, username, password,
								enabled, true, true, true,
								AuthorityUtils.NO_AUTHORITIES, firstName, email);
					}

				});
	}

	@Override
	protected List<GrantedAuthority> loadUserAuthorities(String username) {
		return getJdbcTemplate().query(CUSTOM_AUTHORITIES_BY_USERNAME_QUERY,
				new String[] { username }, new RowMapper<GrantedAuthority>() {
					public GrantedAuthority mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String roleName = rs.getString(2);

						return new SimpleGrantedAuthority(roleName);
					}
				});
	}

}
