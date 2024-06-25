package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "proyecto")
@Data
@NoArgsConstructor
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_proyecto")
    private Long id;
    private String description;

    @OneToOne
    @JoinColumn(name = "cod_diseño_plano", referencedColumnName = "cod_diseño_plano")
    @JsonIgnore
    private DibujoPlano dibujoPlano;

    @ManyToMany
    @JoinTable(
            name = "proyecto_trabajador",
            joinColumns = @JoinColumn(name = "cod_proyecto"),
            inverseJoinColumns = @JoinColumn(name = "cod_trabajador")
    )
    @JsonIgnore
    private List<Trabajador> trabajadores;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrdenPedidoMaterial> ordenesPedidoMaterial;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cod_cronograma", referencedColumnName = "cod_cronograma")
    @JsonIgnore
    private Cronograma cronograma;

    @ManyToOne
    @JoinColumn(name = "cod_encargado", referencedColumnName = "cod_trabajador")
    private Trabajador encargado;

    @Column(name = "Proyecto_Cod", unique = true)
    private String codProyecto;

}
