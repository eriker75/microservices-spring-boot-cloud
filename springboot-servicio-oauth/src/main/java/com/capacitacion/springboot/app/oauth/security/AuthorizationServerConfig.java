package com.capacitacion.springboot.app.oauth.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private InfoAdicionalToken infoAdicionalToken; 
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/* 
	 * Define los permisos que van a tener nuestros servicios Endpoints
	 * para generar y validar token
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// Con permitAll cualquier usuario inclusive puede acceder a la ruta para generar Token
		security.checkTokenAccess("permitAll")
				.checkTokenAccess("isAuthenticated()"); 
				//isAuthenticated: Header Authorization Basic: client id, client secret
				// ? : para usar Bearer
	}

	/* 
	 * Configura los clientes que tienen permiso a nuestra aplicación REST
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient(env.getProperty("config.security.oauth.client.id"))
			.secret(passwordEncoder.encode(env.getProperty("config.security.oauth.client.secret")))
			.scopes("read", "write")
			.authorizedGrantTypes("password", "refresh_token") //authorization_code redireccionamiento sin la necesidad de usuario y contraseña
			.accessTokenValiditySeconds(3600)
			.refreshTokenValiditySeconds(3600)
			.and()
			.withClient(env.getProperty("config.security.oauth.client.id"))
			.secret(passwordEncoder.encode(env.getProperty("config.security.oauth.client.secret")))
			.scopes("read", "write")
			.authorizedGrantTypes("password", "refresh_token") //authorization_code redireccionamiento sin la necesidad de usuario y contraseña
			.accessTokenValiditySeconds(3600)
			.refreshTokenValiditySeconds(3600);
	}

	/* 
	 * Configura AuthenticationManager
	 * Define Token Storage como JasonWebToken(JWT) 
	 * y AccessTokenConverter que guarda la info del usuario en el token
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		 // Agregamos una cadena de Token con información extra que necesitamos 
		 TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		 tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));
		
		  endpoints.authenticationManager(authenticationManager)
		    	.tokenStore(tokenStore())
		  		.accessTokenConverter(accessTokenConverter())
		  		.tokenEnhancer(tokenEnhancerChain);
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	
	// @ TODO hacer que la clave de seguridad del token sea consumida del servicio de configuración
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(env.getProperty("config.security.oauth.jwt.key"));
		return tokenConverter;
	}
}
