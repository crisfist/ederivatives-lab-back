package com.santander.ederivatives.lab.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.santander.ederivatives.lab.jwt.JwtUtil;
import com.santander.ederivatives.lab.user.service.impl.DefaultUsuarioDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private DefaultUsuarioDetailsService defaultUsuarioDetailService;
	
	@Autowired
	private JwtUtil jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
			// es la posicion despues de bearer
			jwtToken = requestTokenHeader.substring(7, requestTokenHeader.length());
			
			try {
				username = this.jwtUtils.extractUsername(jwtToken);
			} catch(ExpiredJwtException je){
				System.out.println("El token a expirado");
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("Token invalido, no empieza con bearer string");
		}
		
		//username = "crisfist";
		
		if(username!= null && SecurityContextHolder.getContext().getAuthentication() == null){
			UserDetails userDetails = this.defaultUsuarioDetailService.loadUserByUsername(username);
			if(this.jwtUtils.validateToken(jwtToken, userDetails)){
				UsernamePasswordAuthenticationToken usernamePasswordAToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAToken);
			}else{
				System.out.println("el token no es valido");
			}			
		}
		filterChain.doFilter(request, response);
	}
}
