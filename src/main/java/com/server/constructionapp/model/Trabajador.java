package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "trabajadores")
@Data
@NoArgsConstructor
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_trabajador")
    private Long id;
    private String fullName;
    @Column(name = "carnet_identidad",unique = true)
    private String identityCard;
    private String rol;
    private String address;
    private String email;
    private Long phone;

    @Column(name = "Trabajador_Cod", unique = true)
    private String codTrabajador;

    @ManyToMany(mappedBy = "trabajadores")
    @JsonIgnore
    private List<Proyecto> proyectos;

    @ManyToMany(mappedBy = "trabajadores")
    @JsonIgnore
    private List<LibroConstruccion> librosConstruccion;

    @OneToMany(mappedBy = "trabajador")
    @JsonIgnore
    private List<PrerequisitoPlano> prerequisitosPlano;


    @OneToMany(mappedBy = "encargado")
    @JsonIgnore
    private List<Proyecto> proyectosEncargados;


}
