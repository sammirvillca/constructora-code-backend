package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cronograma")
@Data
@NoArgsConstructor
public class Cronograma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cronograma")
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToOne(mappedBy = "cronograma")
    @JsonIgnore
    private Proyecto proyecto;

    @Column(name = "Cronograma_Cod", unique = true)
    private String codCronograma;

    @OneToMany(mappedBy = "cronograma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Actividad> actividades = new ArrayList<>();

    public void assignCodCronogramaIfNull(int numero) {
        if (this.codCronograma == null) {
            this.codCronograma = "CRO-" + numero;
        }
    }

    public void addActividad(Actividad actividad) {
        actividades.add(actividad);
        actividad.setCronograma(this);
    }

    public void removeActividad(Actividad actividad) {
        actividades.remove(actividad);
        actividad.setCronograma(null);
    }


}
