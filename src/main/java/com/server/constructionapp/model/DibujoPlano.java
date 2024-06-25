package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "diseño-plano")
@Data
@NoArgsConstructor
public class DibujoPlano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_diseño_plano")
    private Long id;
    private String atmosphere;
    private String circulationFlow;
    private String funcionality;
    private String dimensions;

    @Column(name = "Diseño_Cod", unique = true)
    private String codDiseño;

    private String estado;


    @OneToOne
    @JoinColumn(name = "cod_prerequisito_plano", referencedColumnName = "cod_prerequisito_plano")
    @JsonIgnore
    private PrerequisitoPlano prerequisitoPlano;

    @OneToOne(mappedBy = "dibujoPlano")
    @JsonIgnore
    private ContratoPlano contratoPlano;

    @OneToOne(mappedBy = "dibujoPlano")
    @JsonIgnore
    private Proyecto proyecto;

    @PrePersist
    public void prePersist() {
        if (estado == null) {
            estado = "En Espera";
        }
    }
}
