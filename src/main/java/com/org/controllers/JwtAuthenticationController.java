package com.org.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.JwtTokenUtil;
import com.org.db.JwtUserDetailsService;
import com.org.domain.UserPojo;
@RestController
@CrossOrigin
public class JwtAuthenticationController 
{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@PostMapping(value="/authenticate")
	public String auth(@RequestBody UserPojo authenticationRequest) throws Exception 
	{
		doAuthenticate(authenticationRequest.getUser(),authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUser());
		String sToken = jwtTokenUtil.generateToken(authenticationRequest);
		return sToken;
	}

	private void doAuthenticate(String user, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, password));
		
	}
}
