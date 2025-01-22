package com.example.apirestbiblioteca.repositorios;

import com.example.apirestbiblioteca.modelos.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
}
