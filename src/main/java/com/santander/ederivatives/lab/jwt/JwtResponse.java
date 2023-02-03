package com.santander.ederivatives.lab.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtResponse {
	
	private String token;
	
	public JwtResponse() {}

	public JwtResponse(String token) {
		this.token = token;
	}
}
