package com.example.mx.ruberts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_persona;
    String nombre;
    String apellido;
    String email;
    String telefono;
    String genero;
}
