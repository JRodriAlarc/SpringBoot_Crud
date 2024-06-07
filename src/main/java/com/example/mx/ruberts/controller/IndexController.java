package com.example.mx.ruberts.controller;

import java.util.List;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.mx.ruberts.service.PersonaServicio;
import com.example.mx.ruberts.model.Persona;
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
    PersonaServicio personaServicio;
    private List<Persona> personas;
    private Persona personaSeleccionada;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.personas = personaServicio.listarPersonas();
        personas.forEach((personas) -> logger.info(personas.toString()));
    }

    public void agregarPersona(){
        logger.info("Se crea un objeto personaSelecionada para el caso de agregar");
        this.personaSeleccionada = new Persona();
    }

    public void guardarPersona(){
        logger.info("Persona a Guardar: " + this.personaSeleccionada);
        //Agregar Personas
        if (this.personaSeleccionada.getId_persona() == null) {
            this.personaServicio.guardarPersona(this.personaSeleccionada);
            this.personas.add(this.personaSeleccionada); //Agregar a la interfaz

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Persona Registrada"));
        } else{ //Modificar Personas
            this.personaServicio.guardarPersona(this.personaSeleccionada);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Persona Actualizada"));
        }

        //Ocultar la ventana Modal
        PrimeFaces.current().executeScript("PF('ventanaModalPersona').hide()");
        //Actualizar la tabla
        PrimeFaces.current().ajax().update("forma-personas:mensajes", "forma-personas:personas-tabla");
    }
}
