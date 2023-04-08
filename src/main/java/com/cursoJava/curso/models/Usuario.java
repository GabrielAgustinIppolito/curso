package com.cursoJava.curso.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   private String nombre;
   private String apellido;
   private String telefono;
   private String email;
   private String password;
}
