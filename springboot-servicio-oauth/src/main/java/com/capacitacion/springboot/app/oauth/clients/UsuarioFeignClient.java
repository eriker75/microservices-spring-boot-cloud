package com.capacitacion.springboot.app.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.capacitacion.springboot.app.usuarios.commons.models.entity.Usuario;

@FeignClient(name="servicio-usuarios")
public interface UsuarioFeignClient {

	 /**
	 * Define metodo por el cual el microservicio de OAuth
	 * irá buscar el username para realizar la autenticación 
	 * @param username
	 * @return
	 */
	@GetMapping("/usuarios/search/buscar-username")
	 public Usuario findByUsername(@RequestParam("username") String username);
	
	/**
	 * Método para actualizar las propiedades de un determinado usuario
	 * @param usuario
	 * @param id
	 * @return
	 */
	@PutMapping("/usuarios/{id}")
	public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id);
}
