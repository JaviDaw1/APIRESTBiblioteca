package com.example.apirestbiblioteca.controladores;

import com.example.apirestbiblioteca.modelos.Usuario;
import com.example.apirestbiblioteca.repositorios.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioRepository usuarioRepository;

    public UsuarioController() {
    }

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
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

    @PostMapping("/usuario")
    public ResponseEntity<?> addUsuario(@Valid @RequestBody Usuario usuario) {
        if (!isDniValido(usuario.getDni())) {
            return ResponseEntity.badRequest().body("DNI no válido. Debe tener 8 números seguidos de una letra mayúscula");
        }

        Usuario usuarioPersistido = usuarioRepository.save(usuario);
        return ResponseEntity.ok().body(usuarioPersistido);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<?> updateUsuario(@PathVariable Integer idUsuario, @RequestBody Usuario usuario) {
        if (!isDniValido(usuario.getDni())) {
            return ResponseEntity.badRequest().body("DNI no válido. Debe tener 8 números seguidos de una letra mayúscula");
        }

        if (!usuarioRepository.existsById(idUsuario)) {
            return ResponseEntity.notFound().build();
        }

        usuario.setId(idUsuario);
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

    public static boolean isDniValido(String dni) {
        String dniRegex = "^[0-9]{8}[A-Z]$";
        return Pattern.compile(dniRegex).matcher(dni).matches();
    }
}

