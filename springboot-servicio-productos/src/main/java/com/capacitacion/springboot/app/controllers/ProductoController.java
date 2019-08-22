package com.capacitacion.springboot.app.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capacitacion.springboot.app.productos.models.entity.Producto;
import com.capacitacion.springboot.app.productos.models.service.IProductoService;

/**
 * @author joliveira
 *
 */
@RestController
public class ProductoController {
		
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IProductoService productoService;
	
	/**
	 * @return
	 */
	@GetMapping("/listar")
	public List<Producto> listar() {
		return productoService.findAll().stream().map(producto ->{	
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}
	
	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) throws Exception {
		
		Producto producto = productoService.findById(id);
		producto.setPort(port);
	
		/**
		 * Para probar configuración de manejo de tolerancia en las configuraciones
		 * de los microservicios: application.properties
			try {
				Thread.sleep(2000L);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 **/
		
		return producto;
	}
	
	/**
	 * @param producto
	 * @return
	 */
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {		
		return productoService.save(producto);
	}
	
	
}
