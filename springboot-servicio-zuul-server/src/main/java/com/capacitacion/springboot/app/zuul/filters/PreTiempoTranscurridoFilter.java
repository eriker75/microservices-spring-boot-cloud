package com.capacitacion.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter{

	private static Logger Log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);
	
	
	public boolean shouldFilter() {
		return true;
	}

	public Object run() throws ZuulException {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		Log.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString()));
		
		/**
		 * Vamos a medir el tiempo que un Request toma para ejecutarse
		 */
		Long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);
		
		System.out.println("Estamos accediendo al filtro");
		
		
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	
	
}
