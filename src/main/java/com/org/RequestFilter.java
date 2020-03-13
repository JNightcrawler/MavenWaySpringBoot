package com.org;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.org.db.JwtUserDetailsService;
import com.org.domain.UserPojo;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class RequestFilter extends OncePerRequestFilter {

	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
		String servPath = request.getServletPath();
		if(!servPath.equals("/")||servPath.equals("/authenticate"))
		{
		String username = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUserNameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		
		
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			//UserPojo userDetails = new UserPojo("admin","admin");
			//UserDetails userDetails = (UserDetails) new UserPojo("admin","admin");
			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtTokenUtil.validateToken(jwtToken,  userDetails)) {

				
				
				  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
				  UsernamePasswordAuthenticationToken( userDetails, null,
						  userDetails.getAuthorities()); usernamePasswordAuthenticationToken
				  .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				 
				 
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}
		}
		if(jwtToken == null && !servPath.contains("authenticate"))
		{
			
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
			return ;
		}
		}
		filterChain.doFilter(request, response); 	
		// TODO Auto-generated method stub
		
	}

}
