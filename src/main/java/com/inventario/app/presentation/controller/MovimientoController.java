package com.inventario.app.presentation.controller;


import com.inventario.app.application.service.MovimientoService;
import com.inventario.app.domain.models.Movimientos;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public ResponseEntity<Object> list() {
        var movimientos = movimientoService.findAll();
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@Valid @PathVariable Long id) {
        var movimiento = movimientoService.findById(id);
        return ResponseEntity.ok(movimiento);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Movimientos movimiento) {
        var createdMovimiento = movimientoService.create(movimiento);
        return ResponseEntity.ok(createdMovimiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @PathVariable Long id, @RequestBody Movimientos movimiento) {
        var updatedMovimiento = movimientoService.update(id, movimiento);
        return ResponseEntity.ok(updatedMovimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        movimientoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}