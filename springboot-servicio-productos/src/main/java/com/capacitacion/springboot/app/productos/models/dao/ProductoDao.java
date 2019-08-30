package com.capacitacion.springboot.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.capacitacion.springboot.app.commons.modes.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long>{

}
