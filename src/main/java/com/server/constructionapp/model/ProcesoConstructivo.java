package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proceso-constructivo")
@Data
@NoArgsConstructor
public class ProcesoConstructivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_proceso")
    private Long id;
    private String revision;
    private String pointOfView;
    private String improvements;
    private Boolean meetsTheSchedule;

    @ManyToOne
    @JoinColumn(name = "cod_cronograma", referencedColumnName = "cod_cronograma")
    @JsonIgnore
    private Cronograma cronograma;

    @ManyToOne
    @JoinColumn(name = "cod_trabajador", referencedColumnName = "cod_trabajador")
    @JsonIgnore
    private Trabajador encargado;
}
