package com.santander.ederivatives.lab.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.santander.ederivatives.lab.user.model.Usuario;

@Service
public interface UsuarioService {

	public List<Usuario> findAll();
	
	public Usuario findByName(String name);
	
	public Usuario saveUser(Usuario user);
}
