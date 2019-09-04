package com.capacitacion.springboot.app.zuul.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	private static final String ROLE_ADMIN = "ADMIN";
	private static final String ROLE_USER = "USER";
	
	/* 
	 * Configuraciones relacionadas al Token
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
	
		resources.tokenStore(tokenStore());
	}

	/* 
	 * Configuraciones relacionadas a los endpoits que estarán bajo la autenticación
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		/**
		 *  Matches de reglas a las rutas:
		 *  si queremos dar permiso para todos
		 *  si queremos asignar algún role
		 *  o si se requiere autenticación
		 */
		http.authorizeRequests().antMatchers("/api/security/oauth/**").permitAll()
			.antMatchers(HttpMethod.GET, "/api/productos/listar", "/api/items/listar", "/api/usuarios/usuarios").permitAll()
			.antMatchers(HttpMethod.GET, "/api/productos/ver/{id}", "/api/items/ver/{id}/cantidad/{cantidad}", "/api/usuarios/usuarios/{id}").hasAnyRole(ROLE_ADMIN, ROLE_USER)
			.antMatchers(HttpMethod.POST, "/api/productos/crear", "/api/items/crear", "/api/usuarios/usuarios").hasRole(ROLE_ADMIN)
			.antMatchers(HttpMethod.PUT, "/api/productos/editar/{id}", "/api/items/editar/{id}", "/api/usuarios/usuarios/{id}").hasRole(ROLE_ADMIN)
			.antMatchers(HttpMethod.DELETE, "/api/productos/editar/{id}", "/api/items/editar/{id}", "/api/usuarios/usuarios/{id}").hasRole(ROLE_ADMIN);
	}
		
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		
		// @ TODO hacer que la clave de seguridad del token sea consumida del servicio de configuración
		tokenConverter.setSigningKey("temp_secret_code");
		return tokenConverter;
	}
}
