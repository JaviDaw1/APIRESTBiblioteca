package com.example.apirestbiblioteca.controladores;

import com.example.apirestbiblioteca.modelos.Ejemplar;
import com.example.apirestbiblioteca.modelos.Libro;
import com.example.apirestbiblioteca.repositorios.EjemplarRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
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
    @GetMapping("/{isbn}")
    @Cacheable
    public ResponseEntity<Ejemplar> getEjemplarJson(@PathVariable String isbn) {
        Ejemplar l = this.ejemplarRepository.findById(isbn).get();
        return ResponseEntity.ok(l);
    }

    //POST --> INSERT
    @PostMapping("/ejemplar")
    public ResponseEntity<Ejemplar> addEjemplar(@Valid @RequestBody Ejemplar ejemplar) {
        System.out.println("Entra aqui");
        Ejemplar ejemplarPersistido = this.ejemplarRepository.save(ejemplar);
        return ResponseEntity.ok().body(ejemplarPersistido);
    }

    //POST con Form normal, se trabajará con JSONs normalmente...
    @PostMapping(value = "/ejemplarForm", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ejemplar> addEjemplarForm(@RequestParam Libro isbn,
                                              @RequestParam String estado) {
        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setIsbn(isbn);
        ejemplar.setEstado(estado);
        this.ejemplarRepository.save(ejemplar);
        return ResponseEntity.created(null).body(ejemplar);
    }

    //POST con Form normal y fichero, se trabajará con JSONs normalmente...
    @PostMapping(value = "/ejemplarFormFichero", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ejemplar> addEjemplarFormFichero(@RequestParam Libro isbn,
                                                     @RequestParam String estado) {
        //Datos básicos del ejemplar
        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setIsbn(isbn);
        ejemplar.setEstado(estado);

        //guardado en la bbdd del ejemplar
        this.ejemplarRepository.save(ejemplar);

        //devolución del objeto en formato json para el cliente
        return ResponseEntity.created(null).body(ejemplar);
    }

    //PUT --> UPDATE
    //falta actualizar ficheros
    @PutMapping("/{isbn}")
    public ResponseEntity<Ejemplar> updateEjemplar(@RequestBody Ejemplar ejemplar, @PathVariable String isbn) {
        Ejemplar ejemplarPersistido = ejemplarRepository.save(ejemplar);
        return ResponseEntity.ok().body(ejemplarPersistido);
    }

    //DELETE
    @DeleteMapping("/{isbn}")
    public ResponseEntity<String> deleteEjemplar(@PathVariable String isbn) {
        ejemplarRepository.deleteById(isbn);
        String mensaje = "ejemplar con isbn: " + isbn + " borrado";
        return ResponseEntity.ok().body(mensaje);
    }
}
