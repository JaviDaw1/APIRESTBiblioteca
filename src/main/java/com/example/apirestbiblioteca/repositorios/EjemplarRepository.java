package com.example.apirestbiblioteca.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.apirestbiblioteca.modelos.Ejemplar;

import java.util.List;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Integer> {
    List<Ejemplar> findByIsbn_Isbn(String isbn);
}
