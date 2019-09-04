package com.capacitacion.springboot.app.zuul.oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@RefreshScope
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	private static final String ROLE_ADMIN = "ADMIN";
	private static final String ROLE_USER = "USER";
		
	@Value("${config.security.oauth.jwt.key}")
	private String jwtKey;
		
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
			.antMatchers("/api/productos/**", "/api/items/**", "/api/usuarios/**").hasRole(ROLE_ADMIN)
			.anyRequest().authenticated()
			.and().cors().configurationSource(corsConfigurationSource());
	}
		
	
	/**
	 * Define reglas para Cross Origin Access para que los servicios puedan ser accedidos desde aplicaciones clientes
	 * @return <CorsConfigurationSource> source
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {		
		CorsConfiguration corsConfig = new CorsConfiguration();
		
		// Permite todos los dominios				
		corsConfig.addAllowedOrigin("*");
		// Alternativa corsConfig.setAllowedOrigins(Arrays.asList("*"));
		
		corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));	
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		
		return source;
	}
	
	
	/**
	 * Crea un filtro global de Spring con las configuraciones de CORS
	 * @return <FilterRegistration<CorsFilter> bean
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		
		// @ TODO hacer que la clave de seguridad del token sea consumida del servicio de configuración
		tokenConverter.setSigningKey(jwtKey);
		return tokenConverter;
	}
}
