package com.example.apirestbiblioteca.controladores;

import com.example.apirestbiblioteca.modelos.Ejemplar;
import com.example.apirestbiblioteca.repositorios.EjemplarRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ejemplares")
public class EjemplarController {
    private EjemplarRepository ejemplarRepository;

    public EjemplarController() {
    }

    @Autowired
    public EjemplarController(EjemplarRepository ejemplarRepository) {
        this.ejemplarRepository = ejemplarRepository;
    }

    //GET --> SELECT *
    @GetMapping
    public ResponseEntity<List<Ejemplar>> getEjemplar() {
        List<Ejemplar> lista = this.ejemplarRepository.findAll();
        System.out.println(lista);
        return ResponseEntity.ok(lista);
    }

    //GET BY ISBN --> SELECT BY ISBN
    @GetMapping("/libro/{isbn}")
    public ResponseEntity<List<Ejemplar>> getEjemplaresByLibroIsbn(@PathVariable String isbn) {
        List<Ejemplar> ejemplares = this.ejemplarRepository.findByIsbn_Isbn(isbn);

        if (ejemplares.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ejemplares);
    }

    //POST --> INSERT
    @PostMapping("/ejemplar")
    public ResponseEntity<Ejemplar> addEjemplar(@Valid @RequestBody Ejemplar ejemplar) {
        System.out.println("Entra aqui");
        Ejemplar ejemplarPersistido = this.ejemplarRepository.save(ejemplar);
        return ResponseEntity.ok().body(ejemplarPersistido);
    }

    //PUT --> UPDATE
    //falta actualizar ficheros
    @PutMapping("/{isbn}")
    public ResponseEntity<Ejemplar> updateEjemplar(@RequestBody Ejemplar ejemplar, @PathVariable String isbn) {
        Ejemplar ejemplarPersistido = ejemplarRepository.save(ejemplar);
        return ResponseEntity.ok().body(ejemplarPersistido);
    }

    //DELETE
    @DeleteMapping("/{idEjemplar}")
    public ResponseEntity<String> deleteEjemplar(@PathVariable int idEjemplar) {
        ejemplarRepository.deleteById(idEjemplar);
        String mensaje = "ejemplar con isbn: " + idEjemplar + " borrado";
        return ResponseEntity.ok().body(mensaje);
    }
}
