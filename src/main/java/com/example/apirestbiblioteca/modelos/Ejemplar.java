package com.example.apirestbiblioteca.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ejemplar")
public class Ejemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "isbn", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Libro isbn;

    @ColumnDefault("'Disponible'")
    @Lob
    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "ejemplar")
    @JsonIgnore // Ignora esta propiedad al serializar
    private Set<Prestamo> prestamos = new LinkedHashSet<>();

    public Ejemplar(Integer id, Libro isbn, String estado, Set<Prestamo> prestamos) {
        this.id = id;
        this.isbn = isbn;
        this.estado = estado;
        this.prestamos = prestamos;
    }

    public Ejemplar() {}

    public Integer getId() {
        return id;
    }

    public Libro getIsbn() {
        return isbn;
    }

    public String getEstado() {
        return estado;
    }

    public Set<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIsbn(Libro isbn) {
        this.isbn = isbn;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPrestamos(Set<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    @Override
    public String toString() {
        return "Ejemplar{" +
                "id=" + id +
                ", isbn=" + isbn.getIsbn() +
                ", estado='" + estado + '\'' +
                ", prestamos=" + prestamos +
                '}';
    }
}