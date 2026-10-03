package com.inventario.app.application.service.Impl;

import com.inventario.app.application.service.ProductoService;
import com.inventario.app.domain.models.Producto;
import com.inventario.app.infrastructure.exception.InsufficientStockException;
import com.inventario.app.infrastructure.exception.ResourceNotFoundException;
import com.inventario.app.infrastructure.exception.ValidationException;
import com.inventario.app.infrastructure.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    @Override
    public Producto create(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            throw new ResourceNotFoundException("El nombre del producto no puede estar vacío");
        }
        if (producto.getPrecio() <= 0) {
            throw new ResourceNotFoundException("El precio del producto no puede ser negativo");
        }
        if (producto.getIdProducto() == null || producto.getIdProducto() == 0) {
            throw new ResourceNotFoundException("El ID del producto no puede estar vacío");
        }
        if (productoRepository.existsById(producto.getIdProducto())) {
            throw new ValidationException("El producto con ID " + producto.getIdProducto() + " ya existe");
        }
        if (producto.getStock() < 0) {
            throw new ResourceNotFoundException("El stock del producto no puede ser negativo");
        }

        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El producto con ID " + id + " no existe"));
    }

    @Override
    public void delete(Long id) {
        if (productoRepository == null || !productoRepository.existsById(id)) {
            throw new ValidationException("El producto con ID " + id + " no existe");
        }
        productoRepository.deleteById(id);

    }

    @Override
    public Producto update(Long id, Producto producto) {
        if (producto.getIdProducto() == null || producto.getIdProducto() == 0) {
            throw new ResourceNotFoundException("El ID del producto no puede estar vacío");
        }
        if (!productoRepository.existsById(producto.getIdProducto())) {
            throw new ValidationException("El producto con ID " + producto.getIdProducto() + " no existe");
        }
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo");
        }
        if (producto.getStock() < 0) {
            throw new InsufficientStockException("El stock del producto no puede ser negativo");
        }

        return productoRepository.save(producto);
    }

}
