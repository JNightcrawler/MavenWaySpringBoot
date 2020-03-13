package com.org.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.db.Springdb;

@RestController
public class SpringBootRS
{

	@Autowired
	private Springdb springdb;
	
	
	
	/*
	 * @RequestMapping("/") public String getInput() { return "/index.html"; }
	 */
	 
	
	@RequestMapping("/page")
	public String getInp()
	{
		//springdb.getData();
		
		return "Test ok for page2";
	}
}
