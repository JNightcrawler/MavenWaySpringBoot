package com.org.domain;

public class UserPojo 
{

	public String User;
	public String Password;
	
	public UserPojo () {
		
	}
	public UserPojo(String User, String Password) {
		this.User=User;
		this.Password =Password;
	}
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		this.User = user;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		this.Password = password;
	}
}
