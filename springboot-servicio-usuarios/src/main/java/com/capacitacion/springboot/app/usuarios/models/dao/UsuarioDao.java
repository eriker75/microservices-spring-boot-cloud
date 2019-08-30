package com.capacitacion.springboot.app.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.capacitacion.springboot.app.usuarios.models.entity.Usuario;

/**
 * @author joliveira
 *
 */
@RepositoryRestResource(path="usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {

	/**
	 * @param username
	 * @return
	 */
	public Usuario findByUsername(String username);

	/**
	 * Método implementado usando notación @Query de JPA
	 * 
	 * @param email
	 * @return
	 */
	@Query("select u from Usuario u where u.email=?1")
	public Usuario obtenerPorEmail(String email);
}
