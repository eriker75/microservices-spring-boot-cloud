package com.capacitacion.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter{

	private static Logger Log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);
	
	
	public boolean shouldFilter() {
		return true;
	}

	public Object run() throws ZuulException {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		Log.info("Entrando a post filter");
		
		/**
		 * Vamos a medir el tiempo que un Request toma para ejecutarse
		 */
		Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		Long tiempoFinal = System.currentTimeMillis();
		Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
		
		Log.info(String.format("Tiempo transcurrido en segundos %s seg.", tiempoTranscurrido.doubleValue()/1000.00));
		Log.info(String.format("Tiempo transcurrido en mileseg %s ms.", tiempoTranscurrido));
		
		
		System.out.println("Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
		
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	
	
}
