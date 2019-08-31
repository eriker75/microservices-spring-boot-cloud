package com.capacitacion.springboot.app.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.capacitacion.springboot.app.usuarios.commons.models.entity.Usuario;

/**
 * @author joliveira
 *
 */
@RepositoryRestResource(path = "usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {

	/**
	 * @param username
	 * @return
	 */
	@RestResource(path="buscar-username")
	public Usuario findByUsername(@Param("username") String username);

	/**
	 * Método implementado usando notación @Query de JPA
	 * 
	 * @param email
	 * @return
	 */
	@Query("select u from Usuario u where u.email=?1")
	public Usuario obtenerPorEmail(String email);
}
