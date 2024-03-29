package com.santander.ederivatives.lab.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtRequest {
	
	private String username;
	private String password;
	
	public JwtRequest(){}
	
	public JwtRequest(String username, String password){
		this.username = username;
		this.password = password;
	}

}
