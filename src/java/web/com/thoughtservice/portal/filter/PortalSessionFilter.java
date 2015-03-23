package com.thoughtservice.portal.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.thoughtservice.portal.login.service.PortalUserDetails;

/**
 * Filter class which will set {@link PortalUserDetails} object to every request
 * 
 * @author Shinu
 *
 */
public class PortalSessionFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse arg1,
			FilterChain filterChain) throws IOException, ServletException {

		// Set the PortalUser object to request
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null && context.getAuthentication() != null) {
			if(!"anonymousUser".equals(context
					.getAuthentication().getPrincipal())) {
				PortalUserDetails sessionUser = (PortalUserDetails) context
						.getAuthentication().getPrincipal();
				if (sessionUser != null) {
					request.setAttribute("portalUser", sessionUser);
				}
			}			
		}

		// irrespective of request obect set, proceed with next filter in chain
		// to process
		filterChain.doFilter(request, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
