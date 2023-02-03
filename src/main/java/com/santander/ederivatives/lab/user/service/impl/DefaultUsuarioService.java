package com.santander.ederivatives.lab.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.santander.ederivatives.lab.user.model.Usuario;
import com.santander.ederivatives.lab.user.repository.UsuarioRepository;
import com.santander.ederivatives.lab.user.service.UsuarioService;

@Service
public class DefaultUsuarioService implements UsuarioService{
	
	@Autowired
	private UsuarioRepository  usuarioRepository;
	
	public List<Usuario> findAll(){
		return (List<Usuario>) usuarioRepository.findAll();
	}
	
	public Usuario findByName(String name){
		Usuario user = usuarioRepository.findByUserName(name);
		if(user == null){
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		return user;
	}
	
	public Usuario saveUser(Usuario user){
		return usuarioRepository.save(user);
	}

}
