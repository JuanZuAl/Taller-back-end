package com.inventario.app.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movimientos")
public class Movimientos {
    @Id
    @Column(name = "id_movimiento", nullable = false)
    private Long idMovimiento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto", referencedColumnName = "id_producto", nullable = false)
    private Producto producto;
    @Column(name = "cantidad", nullable = false)
    @Positive (message = "La cantidad del movimiento debe ser positiva")
    private double cantidad;
    @Column (name = "tipo_movimiento", nullable = false)
    @NotNull(message = "El tipo de movimiento no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private TipoMoviemiento tipoMovimiento;

}