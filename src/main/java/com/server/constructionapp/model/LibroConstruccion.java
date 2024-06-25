package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "libro-construccion")
@Data
@NoArgsConstructor
public class LibroConstruccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_libro")
    private Long id;
    private LocalDate constructionDate;
    private String activity;
    private String description;

    @ManyToOne
    @JoinColumn(name = "cod_proyecto", referencedColumnName = "cod_proyecto")
    @JsonIgnore
    private Proyecto proyecto;

    @ManyToMany
    @JoinTable(
            name = "libro_construccion_trabajador",
            joinColumns = @JoinColumn(name = "cod_libro"),
            inverseJoinColumns = @JoinColumn(name = "cod_trabajador")
    )
    @JsonIgnore
    private List<Trabajador> trabajadores;
}
