package com.capacitacion.springboot.app.productos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capacitacion.springboot.app.commons.modes.entity.Producto;
import com.capacitacion.springboot.app.productos.models.dao.ProductoDao;

/**
 * @author joliveira
 *
 */
@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private ProductoDao productoDao;


	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}


	public Producto findById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	
	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoDao.save(producto);
	}

	
	@Override
	@Transactional
	public void deleteById(Long id) {
		productoDao.deleteById(id);
	}

	
}
