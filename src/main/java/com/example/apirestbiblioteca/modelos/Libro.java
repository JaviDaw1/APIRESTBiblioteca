package com.example.apirestbiblioteca.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "libro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    @Id
    @Size(max = 20)
    @Column(name = "isbn", nullable = false, length = 20)
    @Pattern(regexp = "^97[89]-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d$\n", message = "El isbn debe tener un formato valido")
    private String isbn;

    @Size(max = 200)
    @NotNull
    @NotBlank
    @Column(name = "titulo", nullable = false, length = 200)
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9 ]*$\n", message = "El título debe empezar por letra mayúscula")
    private String titulo;

    @Size(max = 100)
    @NotNull
    @NotBlank
    @Column(name = "autor", nullable = false, length = 100)
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9 ]*$\n", message = "El autor debe empezar por mayúscula")
    private String autor;
}

