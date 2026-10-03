package com.inventario.app.application.service;

import com.inventario.app.domain.models.Movimientos;

import java.util.List;

public interface MovimientoService {

    Movimientos create(Movimientos movimientos);
    List<Movimientos> findAll();
    Movimientos findById(Long id);
    void delete(Long id);
    Movimientos update(Long id, Movimientos movimientos);
}
