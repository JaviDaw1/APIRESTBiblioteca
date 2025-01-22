package com.example.apirestbiblioteca.repositorios;

import com.example.apirestbiblioteca.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrosRepository extends JpaRepository<Libro, String> {
}
