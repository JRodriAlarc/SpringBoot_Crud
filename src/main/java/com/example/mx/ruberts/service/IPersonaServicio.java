package com.example.mx.ruberts.service;

import java.util.List;
import com.example.mx.ruberts.model.Persona;

public interface IPersonaServicio {
    public List<Persona> listarPersonas();

    public Persona buscPersonaPorId(Integer id_persona);
    
    public void guardarPersona(Persona persona);
    
    public void eliminarPersona(Persona persona);
}
