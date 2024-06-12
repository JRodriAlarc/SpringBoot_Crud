package com.example.mx.ruberts.controller;

import java.util.List;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.mx.ruberts.service.InventarioServicio;
import com.example.mx.ruberts.model.Inventario;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;

@Component
@Data
@ViewScoped
public class IndexController {
    
    @Autowired
    InventarioServicio inventarioServicio;
    private List<Inventario> inventario;
    private Inventario inventarioSelecionado;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.inventario = inventarioServicio.listarInventario();
        inventario.forEach((inventario) -> logger.info(inventario.toString()));
    }

    public void agregarProducto(){
        logger.info("Se crea un objeto 'inventarioSelecionado' para el caso de agregar");
        this.inventarioSelecionado = new Inventario();
    }

    public void guardarProducto(){
        logger.info("Producto a Guardar: " + this.inventarioSelecionado);
        //Agregar inventario
        if (this.inventarioSelecionado.getIdProducto() == null) {
            this.inventarioServicio.guardarProducto(this.inventarioSelecionado);
            this.inventario.add(this.inventarioSelecionado); //Agregar a la interfaz

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Registrado"));
        } else{ //Modificar inventario
            this.inventarioServicio.guardarProducto(this.inventarioSelecionado);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Actualizado"));
        }

        //Ocultar la ventana Modal
        PrimeFaces.current().executeScript("PF('ventanaModalProducto').hide()");
        
        //Actualizar la tabla
        PrimeFaces.current().ajax().update("forma-inventario:mensajes", "forma-inventario:inventario-tabla");

        //Resetear el Objeto seleccionado de la tabla
        this.inventarioSelecionado = null;
    }

    public void eliminarProducto(){
        logger.info("Producto Eliminada: " + this.inventarioSelecionado);
        this.inventarioServicio.eliminarProducto(this.inventarioSelecionado);
        
        //Eliminar la Producto de la lista en Memoria
        this.inventario.remove(this.inventarioSelecionado);

        //Resetear el Objeto seleccionado de la tabla
        this.inventarioSelecionado = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Eliminada"));

        //Actualizar la tabla
        PrimeFaces.current().ajax().update("forma-inventario:mensajes", "forma-inventario:inventario-tabla");
    }
}
