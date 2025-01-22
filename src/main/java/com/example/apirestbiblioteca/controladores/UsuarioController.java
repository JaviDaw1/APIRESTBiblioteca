package com.example.apirestbiblioteca.controladores;

import com.example.apirestbiblioteca.modelos.Usuario;
import com.example.apirestbiblioteca.repositorios.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioRepository usuarioRepository;

    public UsuarioController() {
    }

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    //GET --> SELECT *
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuario() {
        List<Usuario> lista = this.usuarioRepository.findAll();
        System.out.println(lista);
        return ResponseEntity.ok(lista);
    }

    //GET BY ISBN --> SELECT BY ISBN
    @GetMapping("/{idUsuario}")
    @Cacheable
    public ResponseEntity<Usuario> getUsuarioJson(@PathVariable Integer idUsuario) {
        Usuario l = this.usuarioRepository.findById(idUsuario).get();
        return ResponseEntity.ok(l);
    }

    //POST --> INSERT
    @PostMapping("/usuario")
    public ResponseEntity<Usuario> addUsuario(@Valid @RequestBody Usuario usuario) {
        System.out.println("Entra aqui");
        Usuario usuarioPersistido = this.usuarioRepository.save(usuario);
        return ResponseEntity.ok().body(usuarioPersistido);
    }

    //PUT --> UPDATE
    //falta actualizar ficheros
    @PutMapping("/{idUsuario}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioPersistido = usuarioRepository.save(usuario);
        return ResponseEntity.ok().body(usuarioPersistido);
    }

    //DELETE
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
        String mensaje = "usuario con isbn: " + idUsuario + " borrado";
        return ResponseEntity.ok().body(mensaje);
    }
}

