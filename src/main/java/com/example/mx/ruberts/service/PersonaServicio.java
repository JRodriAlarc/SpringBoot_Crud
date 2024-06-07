package com.example.mx.ruberts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mx.ruberts.model.Persona;
import com.example.mx.ruberts.repository.PersonaRepositorio;

@Service
public class PersonaServicio implements IPersonaServicio{

    @Autowired
    private PersonaRepositorio personaRepositorio;

    @Override
    public List<Persona> listarPersonas() {
        return personaRepositorio.findAll();
        // throw new UnsupportedOperationException("Unimplemented method 'listarPersonas'");
    }

    @Override
    public Persona buscPersonaPorId(Integer id_persona) {
        Persona persona = personaRepositorio.findById(id_persona).orElse(null);
        return persona;
        // throw new UnsupportedOperationException("Unimplemented method 'buscPersonaPorId'");
    }

    @Override
    public void guardarPersona(Persona persona) {
        personaRepositorio.save(persona);
        // throw new UnsupportedOperationException("Unimplemented method 'guardarPersona'");
    }

    @Override
    public void eliminarPersona(Persona persona) {
        personaRepositorio.delete(persona);
        // throw new UnsupportedOperationException("Unimplemented method 'eliminarPersona'");
    }

}
