package com.capacitacion.springboot.app.usuarios.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.capacitacion.springboot.app.usuarios.models.entity.Usuario;

/**
 * @author joliveira
 *
 */
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long>{

	public Usuario findByUsername(String username);
}
