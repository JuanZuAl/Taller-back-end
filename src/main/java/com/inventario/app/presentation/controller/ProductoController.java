package com.inventario.app.presentation.controller;

import com.inventario.app.application.service.ProductoService;
import com.inventario.app.domain.models.Producto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> findAll() {
        List<Producto> productos = productoService.findAll();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@Valid @PathVariable Long id) {
        var producto = productoService.findById(id);
        return ResponseEntity.ok(producto);
    }
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Producto producto) {
        var createdProducto = productoService.create(producto);
        return ResponseEntity.ok(createdProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody Producto producto) {
        var updatedProducto = productoService.update(id, producto);
        return ResponseEntity.ok(updatedProducto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
