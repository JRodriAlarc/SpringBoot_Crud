package com.example.mx.ruberts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mx.ruberts.model.Inventario;
import com.example.mx.ruberts.repository.InventarioRepositorio;

@Service
public class InventarioServicio implements IInventarioServicio{

    @Autowired
    private InventarioRepositorio inventarioRepositorio;

    @Override
    public List<Inventario> listarInventario() {
        return inventarioRepositorio.findAll();
        // throw new UnsupportedOperationException("Unimplemented method 'listarproductos'");
    }

    @Override
    public Inventario buscarProductoPorId(Integer idProducto) {
        Inventario producto = inventarioRepositorio.findById(idProducto).orElse(null);
        return producto;
        // throw new UnsupportedOperationException("Unimplemented method 'buscproductoPorId'");
    }

    @Override
    public void guardarProducto(Inventario producto) {
        inventarioRepositorio.save(producto);
        // throw new UnsupportedOperationException("Unimplemented method 'guardarproducto'");
    }

    @Override
    public void eliminarProducto(Inventario producto) {
        inventarioRepositorio.delete(producto);
        // throw new UnsupportedOperationException("Unimplemented method 'eliminarproducto'");
    }

}
