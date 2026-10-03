package com.inventario.app.infrastructure.repository;

import com.inventario.app.domain.models.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimientos, Long> {
}
