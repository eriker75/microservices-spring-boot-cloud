package com.capacitacion.springboot.app.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.capacitacion.springboot.app.oauth.services.IUsuarioService;
import com.capacitacion.springboot.app.usuarios.commons.models.entity.Usuario;

/**
 * @author joliveira
 * Clase del tipo Spring Security responsable por agregarle información extra a la Json Web Token
 */
@Component
public class InfoAdicionalToken implements TokenEnhancer{

	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		 
		Usuario usuario = usuarioService.findAllUserInfoByUsername(authentication.getName());
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		info.put("correo", usuario.getEmail());		
		
		// Buscamos la implementación concreta
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}	
}
