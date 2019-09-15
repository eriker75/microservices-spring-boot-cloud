package com.capacitacion.springboot.app.oauth.services;

import com.capacitacion.springboot.app.usuarios.commons.models.entity.Usuario;

/**
 * @author joliveira
 * Usaremos esta interface para obtener todos los datos del usuario
 */
public interface IUsuarioService {

	public Usuario findAllUserInfoByUsername(String username);
		
	public Usuario update(Usuario usuario,Long id);
}
