package com.capacitacion.springboot.app.productos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capacitacion.springboot.app.productos.models.dao.ProductoDao;
import com.capacitacion.springboot.app.productos.models.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private ProductoDao productoDao;

	
	/* (non-Javadoc)
	 * @see com.capacitacion.springboot.app.productos.models.service.IProductoService#findAll()
	 */
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.capacitacion.springboot.app.productos.models.service.IProductoService#findById(java.lang.Long)
	 */
	public Producto findById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	
}
