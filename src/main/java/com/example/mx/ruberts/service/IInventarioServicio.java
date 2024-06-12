package com.example.mx.ruberts.service;

import java.util.List;
import com.example.mx.ruberts.model.Inventario;

public interface IInventarioServicio {
    public List<Inventario> listarInventario();

    public Inventario buscarProductoPorId(Integer idProducto);
    
    public void guardarProducto(Inventario producto);
    
    public void eliminarProducto(Inventario producto);
}
