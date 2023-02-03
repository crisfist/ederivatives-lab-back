package com.santander.ederivatives.lab.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.santander.ederivatives.lab.user.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query(value = "SELECT m FROM Usuario m WHERE m.userName=:userName")
	public Usuario findByUserName(@Param("userName")String userName);

}
