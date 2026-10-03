package com.inventario.app.domain.models;

import com.inventario.app.infrastructure.exception.InsufficientStockException;
import jakarta.persistence.*;
import com.inventario.app.infrastructure.exception.ValidationException;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos")
public class Producto {
    @Id
    @Column(name = "id_producto", nullable = false)
    private Long idProducto;
    @Column(name = "nombre", nullable = false, length = 100)
    @NotEmpty(message = "El nombre del producto no puede estar vacío")
    @Size(max = 100, message = "El nombre del producto no puede tener más de 100 caracteres")
    private String nombre;
    @Column(name = "description", length = 255)
    @Size (max = 255, message = "La descripción del producto no puede tener más de 255 caracteres")
    private String description;
    @Column(name = "precio", nullable = false)
    @PositiveOrZero(message = "El precio del producto no puede ser negativo")
    private double precio;
    @Column (name = "stock", nullable = false)
    @PositiveOrZero(message = "El stock del producto no puede ser negativo")
    private double stock;

    public void sumarStock(double cantidad) {
        if  (cantidad <= 0) {
            throw new ValidationException("El cantidad del producto debe de ser mayor a 0");
        }
        this.stock += cantidad;
    }

    public void restarStock(double cantidad) {
        if (cantidad <= 0) {
            throw new ValidationException("El cantidad del producto debe de ser mayor a 0");
        }
        if (cantidad > this.stock) {
            throw new InsufficientStockException("No hay suficiente stock para restar la cantidad solicitada");
        }
        this.stock -= cantidad;
    }

    @Override
    public String toString() {
        return "Producto [id=" + idProducto + ", nombre=" + nombre + ", description=" + description + ", precio=" + precio + ", stock=" + stock + "]";
    }
}
