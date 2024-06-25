package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "entrega")
@Data
@NoArgsConstructor
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_entrega")
    private Long id;
    private LocalDate deadline;

    @OneToOne
    @JoinColumn(name = "cod_proyecto", referencedColumnName = "cod_proyecto")
    @JsonIgnore
    private  Proyecto proyecto;

    @OneToOne
    @JoinColumn(name = "cod_cliente", referencedColumnName = "cod_cliente")
    @JsonIgnore
    private Cliente cliente;

    @Column(name = "Entrega_Cod", unique = true)
    private String codEntrega;
}
