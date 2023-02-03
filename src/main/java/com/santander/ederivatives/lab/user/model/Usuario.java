package com.santander.ederivatives.lab.user.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.santander.ederivatives.lab.jwt.Authority;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="USUARIO")
@Getter @Setter
@NoArgsConstructor
@ToString
public class Usuario implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="user_name")
	private String userName;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String phone;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> autoridades = new HashSet<Authority>();
		autoridades.add(new Authority("ADMIN"));
		
		return autoridades;
	}
	@Override
	public String getUsername() {
		return userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}

}
