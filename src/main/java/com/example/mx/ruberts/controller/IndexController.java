package com.example.mx.ruberts.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.mx.ruberts.service.PersonaServicio;
import com.example.mx.ruberts.model.Persona;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;

@Component
@Data
@ViewScoped
public class IndexController {
    
    @Autowired
    PersonaServicio personaServicio;
    private List<Persona> personas;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.personas = personaServicio.listarPersonas();
        personas.forEach((personas) -> logger.info(personas.toString()));
    }
}
