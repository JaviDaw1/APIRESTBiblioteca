package com.example.apirestbiblioteca.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 15)
    @NotNull
    @NotBlank
    @Column(name = "dni", nullable = false, length = 15)
    private String dni;

    @Size(max = 100)
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "El nombre solo puede contener caracteres alfanuméricos y espacios")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Size(max = 100)
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "El email debe ser de dominio Gmail (ejemplo: usuario@gmail.com)")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(min = 4, max = 12)
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "La contraseña debe ser alfanumérica y tener entre 4 y 12 caracteres")
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(normal|administrador)$", message = "El tipo solo puede ser 'normal' o 'administrador'")
    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    @Column(name = "penalizacionHasta")
    private LocalDate penalizacionHasta;
}