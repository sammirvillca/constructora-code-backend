package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "aceptacion-plano")
@Data
@NoArgsConstructor
public class AceptacionPlano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_aceptacion_plano")
    private Long id;
    private LocalDate acceptanceDate;
    private String comments;
    private String estado;


    @OneToOne
    @JoinColumn(name = "cod_diseño_plano", referencedColumnName = "cod_diseño_plano")
    @JsonIgnore
    private DibujoPlano dibujoPlano;
}
