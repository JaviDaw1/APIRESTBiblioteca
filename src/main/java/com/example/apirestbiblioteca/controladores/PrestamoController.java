package com.example.apirestbiblioteca.controladores;

import com.example.apirestbiblioteca.modelos.Ejemplar;
import com.example.apirestbiblioteca.modelos.Prestamo;
import com.example.apirestbiblioteca.modelos.Usuario;
import com.example.apirestbiblioteca.repositorios.PrestamoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/prestamos")
public class PrestamoController {
    private PrestamoRepository prestamoRepository;

    public PrestamoController() {
    }

    @Autowired
    public PrestamoController(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    //GET --> SELECT *
    @GetMapping
    public ResponseEntity<List<Prestamo>> getPrestamo() {
        List<Prestamo> lista = this.prestamoRepository.findAll();
        System.out.println(lista);
        return ResponseEntity.ok(lista);
    }

    //GET BY ISBN --> SELECT BY ISBN
    @GetMapping("/{idPrestamo}")
    @Cacheable
    public ResponseEntity<Prestamo> getPrestamoJson(@PathVariable Integer idPrestamo) {
        Prestamo l = this.prestamoRepository.findById(idPrestamo).get();
        return ResponseEntity.ok(l);
    }

    //POST --> INSERT
    @PostMapping("/prestamo")
    public ResponseEntity<Prestamo> addPrestamo(@Valid @RequestBody Prestamo prestamo) {
        System.out.println("Entra aqui");
        Prestamo prestamoPersistido = this.prestamoRepository.save(prestamo);
        return ResponseEntity.ok().body(prestamoPersistido);
    }

    //PUT --> UPDATE
    //falta actualizar ficheros
    @PutMapping("/{idPrestamo}")
    public ResponseEntity<Prestamo> updatePrestamo(@RequestBody Prestamo prestamo, @PathVariable int idPrestamo) {
        Prestamo prestamoPersistido = prestamoRepository.save(prestamo);
        return ResponseEntity.ok().body(prestamoPersistido);
    }

    //DELETE
    @DeleteMapping("/{idPrestamo}")
    public ResponseEntity<String> deletePrestamo(@PathVariable Integer idPrestamo) {
        prestamoRepository.deleteById(idPrestamo);
        String mensaje = "prestamo con isbn: " + idPrestamo + " borrado";
        return ResponseEntity.ok().body(mensaje);
    }
}
