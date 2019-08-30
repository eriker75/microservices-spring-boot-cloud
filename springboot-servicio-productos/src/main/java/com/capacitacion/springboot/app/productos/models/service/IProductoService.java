package com.capacitacion.springboot.app.productos.models.service;

import java.util.List;

import com.capacitacion.springboot.app.commons.modes.entity.Producto;

/**
 * @author joliveira
 *
 */
public interface IProductoService {

	/**
	 * @return
	 */
	public List<Producto> findAll();

	/**
	 * @param id
	 * @return
	 */
	public Producto findById(Long id);
	
	/**
	 * @param producto
	 * @return
	 */
	public Producto save(Producto producto);
	
	/**
	 * @param id
	 */
	public void deleteById(Long id);
}
