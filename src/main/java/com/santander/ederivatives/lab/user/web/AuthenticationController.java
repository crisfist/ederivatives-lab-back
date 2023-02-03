package com.santander.ederivatives.lab.user.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.santander.ederivatives.lab.jwt.JwtRequest;
import com.santander.ederivatives.lab.jwt.JwtResponse;
import com.santander.ederivatives.lab.jwt.JwtUtil;
import com.santander.ederivatives.lab.user.model.Usuario;
import com.santander.ederivatives.lab.user.service.impl.DefaultUsuarioDetailsService;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private DefaultUsuarioDetailsService defaultUsuarioDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/generate-token")	
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			this.autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Usuario no encontrado");
		}
		
		UserDetails usuarioDetails = defaultUsuarioDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtil.generateToken(usuarioDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void autenticar(String username, String password) throws Exception{
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("Usuario deshabilitado "+ e.getMessage());
		} catch(BadCredentialsException be){
			throw new Exception("Credenciales invalidas " +be.getMessage());
		}
	}
	
	@GetMapping("/actual-usuario")
	public Usuario obtenerUsuarioActual(Principal principal){
		return (Usuario) this.defaultUsuarioDetailsService.loadUserByUsername(principal.getName());
	}

}
