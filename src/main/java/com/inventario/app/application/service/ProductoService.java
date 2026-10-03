package com.inventario.app.application.service;

import com.inventario.app.domain.models.Producto;

import java.util.List;

public interface ProductoService {

    Producto create(Producto producto);
    List<Producto> findAll();
    Producto findById(Long id);
    void delete(Long id);
    Producto update(Long id, Producto producto);


}
