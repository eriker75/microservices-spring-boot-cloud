package com.capacitacion.springboot.app.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capacitacion.springboot.app.oauth.clients.UsuarioFeignClient;
import com.capacitacion.springboot.app.usuarios.commons.models.entity.Usuario;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService{

	private Logger log = LoggerFactory.getLogger(UsuarioService.class);
	
	/**
	 * Cliente Rest Feign responsable de conectarse al microservice de Usuarios 
	 */
	@Autowired
	private UsuarioFeignClient client;
	
	
	/* 
	 * Método que se debe implementar al usar Spring Security para obtener los datos del usuario
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = client.findByUsername(username);
		
		if(usuario == null) {
			log.error("Error en el login, no existe el usuario '" + username + "' en el sistema");
			throw new UsernameNotFoundException("Error en el login, no existe el usuario '" + username + "' en el sistema");
			
		}
		
		// Lista de Roles en Spring Security es del tipo GrantedAuthority
		List<GrantedAuthority> authorities = usuario.getRoles()
											.stream()
											.map(role -> new SimpleGrantedAuthority(role.getNombre()))
											.peek(authority -> log.info("Role: " + authority.getAuthority()))
											.collect(Collectors.toList());
		
		log.info("Usuario autenticado: " + username);
		
		/**
		 *  loadUserByUsername espera un tipo de clase UserDetails donde los roles son del tipo GrantAuthority authorities
		 */
		return new User(usuario.getUsername(), 
						usuario.getPassword(),
						usuario.getEnabled(),
						true,
						true, 
						true,
						authorities);
	}


	/* 
	 * Busca detalles del usuario comunicandose con el microservicio de Usuarios
	 */
	@Override
	public Usuario findAllUserInfoByUsername(String username) {		
		return client.findByUsername(username);
	}	
}
