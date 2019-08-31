package com.capacitacion.springboot.app.usuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.capacitacion.springboot.app.usuarios.models.entity.Role;
import com.capacitacion.springboot.app.usuarios.models.entity.Usuario;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer{

	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Usuario.class, Role.class);		
	}
}
