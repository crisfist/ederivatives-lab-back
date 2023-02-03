package com.santander.ederivatives.lab.user.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santander.ederivatives.lab.user.model.Usuario;
import com.santander.ederivatives.lab.user.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/")
    public ResponseEntity<List<Usuario>> findAllUsers() {
        return ResponseEntity.ok(usuarioService.findAll());
    }
	
	@GetMapping("/{userName}")
    public ResponseEntity<Usuario> findByName(@PathVariable("userName") String userName) {

        if (userName != null) {
            return ResponseEntity.ok(usuarioService.findByName(userName));
        }else{
        	return null;
        }
    }
	
	@PostMapping("/")
	public ResponseEntity<Usuario> saveUser(@RequestBody Usuario user){
		if(user != null){
			return ResponseEntity.ok(usuarioService.saveUser(user));
		}else{
			return null;
		}
		
	}
}