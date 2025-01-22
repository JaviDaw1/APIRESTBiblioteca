package com.example.apirestbiblioteca.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.apirestbiblioteca.modelos.Ejemplar;

public interface EjemplarRepository extends JpaRepository<Ejemplar, String> {
}
