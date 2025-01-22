package com.example.apirestbiblioteca.repositorios;

import com.example.apirestbiblioteca.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
