package com.capacitacion.springboot.app.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.capacitacion.springboot.app.oauth.clients.UsuarioFeignClient;
import com.capacitacion.springboot.app.usuarios.commons.models.entity.Usuario;

public class UsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioFeignClient client;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = client.findByUsername(username);
		
		// Lista de Roles en Spring Security es del tipo GrantedAuthority
		List<GrantedAuthority> authorities = usuario.getRoles()
											.stream()
											.map(role -> new SimpleGrantedAuthority(role.getNombre()))
											.collect(Collectors.toList());
		
		return new User(usuario.getUsername(), 
						usuario.getPassword(),
						usuario.getEnabled(),
						true,
						true, 
						true,
						authorities);
	}	
}
