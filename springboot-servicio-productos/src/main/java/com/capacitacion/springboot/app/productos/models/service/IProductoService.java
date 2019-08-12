package com.capacitacion.springboot.app.productos.models.service;

import java.util.List;

import com.capacitacion.springboot.app.productos.models.entity.Producto;

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
	
}
