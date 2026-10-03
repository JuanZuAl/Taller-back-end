package com.inventario.app.application.service.Impl;

import com.inventario.app.application.service.MovimientoService;
import com.inventario.app.domain.models.Movimientos;
import com.inventario.app.domain.models.Producto;
import com.inventario.app.infrastructure.exception.ResourceNotFoundException;
import com.inventario.app.infrastructure.exception.ValidationException;
import com.inventario.app.infrastructure.repository.MovimientoRepository;
import com.inventario.app.infrastructure.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovimientoServiceImpl implements MovimientoService {

    private MovimientoRepository movimientoRepository;
    private ProductoRepository productoRepository;
    public MovimientoServiceImpl(MovimientoRepository movimientoRepository,
                                 ProductoRepository productoRepository) {
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public Movimientos create(Movimientos movimientos) {
        if (movimientoRepository.existsById(movimientos.getIdMovimiento())) {
            throw new ValidationException("El movimiento con ID " + movimientos.getIdMovimiento() + " ya existe en la base de datos");
        }
        if (movimientos.getProducto() == null || movimientos.getProducto().getIdProducto() == null) {
            throw new ValidationException("El movimiento debe indicar un producto");
        }
        Producto producto = productoRepository.findById(movimientos.getProducto().getIdProducto())
                .orElseThrow(() -> new ResourceNotFoundException("El producto con ID " + movimientos.getProducto().getIdProducto() + " no existe"));

        switch (movimientos.getTipoMovimiento()) {
            case ENTRADA -> producto.sumarStock(movimientos.getCantidad());
            case SALIDA  -> producto.restarStock(movimientos.getCantidad());
        }
        productoRepository.save(producto);
        movimientos.setProducto(producto);
        return movimientoRepository.save(movimientos);
    }

    @Override
    public List<Movimientos> findAll() {
        return movimientoRepository.findAll();
    }

    @Override
    public Movimientos findById(Long id) {
        return movimientoRepository.findById(id).orElseThrow(() -> new ValidationException("El movimiento con ID " + id + " no existe en la base de datos"));
    }

    @Override
    public void delete(Long id) {
        if (id == null || !movimientoRepository.existsById(id)) {
            throw new ValidationException("El ID del movimiento no puede ser nulo o no existe en la base de datos");
        }
        movimientoRepository.deleteById(id);
    }

    @Override
    public Movimientos update(Long id, Movimientos movimientos) {
        if (movimientos == null) {
            throw new ValidationException("El movimiento no puede ser nulo");
        }
        return movimientoRepository.save(movimientos);
    }


}
