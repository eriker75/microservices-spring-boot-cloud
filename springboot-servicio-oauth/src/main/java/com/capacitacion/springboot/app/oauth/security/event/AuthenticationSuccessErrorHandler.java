package com.capacitacion.springboot.app.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.capacitacion.springboot.app.oauth.services.IUsuarioService;
import com.capacitacion.springboot.app.usuarios.commons.models.entity.Usuario;

import brave.Tracer;
import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private Tracer tracer;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		String mensaje = "Success Login: " + user.getUsername();
		System.out.println(mensaje);
		log.info(mensaje);

		Usuario usuario = usuarioService.findAllUserInfoByUsername(authentication.getName());
		
		// Reiniciamos el contador de intentos
		if (usuario.getIntentos() != null && usuario.getIntentos() > 0) {
			usuario.setIntentos(0);
			usuarioService.update(usuario, usuario.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String mensaje = "Success Login: " + exception.getMessage();
		System.out.println(mensaje);
		log.error(mensaje);

		// Agregamos un try-catch porque como estamos lidiando con falla, es posible que
		// el objeto Usuario no exista
		try {
			StringBuilder errors = new StringBuilder();
			errors.append(mensaje);
			
			Usuario usuario = usuarioService.findAllUserInfoByUsername(authentication.getName());

			// Verificamos si el valor de la propriedad intentos es nula sino asignamos un
			// valor cero
			if (usuario.getIntentos() == null) {
				usuario.setIntentos(0);
			}

			// Incrementamos el numero de intentos en caso de falla obteniendo el total de
			// intentos actual
			log.info(String.format("Intentos actual es de: " + usuario.getIntentos()));
			usuario.setIntentos(usuario.getIntentos() + 1);
			log.info(String.format("Intentos después es de: " + usuario.getIntentos()));
			errors.append(" - " + "Intentos después es de: " + usuario.getIntentos());

			if (usuario.getIntentos() >= 3) {
				String errorMaxIntentos = String.format("El usuario %s des-habilitado por máximos intentos.", usuario.getUsername());
				log.error(errorMaxIntentos);
				errors.append(" - " + errorMaxIntentos);
				usuario.setEnabled(false);
			}

			usuarioService.update(usuario, usuario.getId());

			tracer.currentSpan().tag("error.mensaje", errors.toString());
			
		} catch (FeignException e) {
			log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
		}
	}
}
